package com.example.meaudiorecorder

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.example.meaudiorecorder.RoomDb.AppDatabase
import com.example.meaudiorecorder.RoomDb.DbRecord
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Date
import java.util.Locale

const val REQUEST_CODE = 200

class MainActivity : AppCompatActivity(), Timer.OnSetTimeListener {
    private lateinit var recordBtn: ImageButton
    private lateinit var cancelBtn: ImageButton
    private lateinit var listBtn: ImageButton
    private lateinit var closeBottomSheet: MaterialButton
    private lateinit var bottomsheet: LinearLayout
    private lateinit var textInput: TextInputEditText
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>
    private lateinit var backgroundView: View
    private lateinit var doneBtn: MaterialButton
    private lateinit var database: AppDatabase
    private var isRecordPresent = false
    private var timerDuration: Long = 0L

    private lateinit var timer: Timer
    private lateinit var timerText: TextView
    private var recorder: MediaRecorder? = null
    private lateinit var vibrator: Vibrator
    private lateinit var waveformView: WaveFormView
    private val permissions = arrayOf(Manifest.permission.RECORD_AUDIO)
    private var permissionGranted = false
    private var isRecording = false
    private var isPaused = false
    private lateinit var filename: String
    private lateinit var filePath: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "AudioRecord"
        ).build()

        recordBtn = findViewById(R.id.recordButton)
        cancelBtn = findViewById(R.id.cancel_button)
        timerText = findViewById(R.id.timerDisplay)
        waveformView = findViewById(R.id.waveformView)
        backgroundView = findViewById(R.id.backgroundView)
        closeBottomSheet = findViewById(R.id.closeBottomsheet)
        textInput = findViewById(R.id.textInput)
        doneBtn = findViewById(R.id.doneBtn)
        bottomsheet = findViewById(R.id.bottomsheet)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomsheet)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
        bottomSheetBehavior.isDraggable = false


        listBtn = findViewById(R.id.listButton)

        timer = Timer(this)
        vibrator = this.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        permissionGranted = ContextCompat.checkSelfPermission(
            this,
            permissions[0]
        ) == PackageManager.PERMISSION_GRANTED
        if (!permissionGranted) {
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
        }



        recordBtn.setOnClickListener {
            if (!permissionGranted) {
                ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
            } else {
                val vibrationEffect =
                    VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
                vibrator.vibrate(vibrationEffect)
                if (!isRecording) {
                    if (isPaused) {
                        resumeRecording()
                    } else {
                        startRecord()
                        Toast.makeText(this, "Start recording", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    pauseRecord()
                }
            }


        }

        cancelBtn.setOnClickListener {
            stopRecord()
            deleteRecord(this, filename)
        }

        backgroundView.setOnClickListener {
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN
            backgroundView.visibility = View.GONE
        }



        listBtn.setOnClickListener {

            if (isRecording || isPaused) {
                textInput.setText(filename)
                stopRecord()
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                backgroundView.visibility = View.VISIBLE

            } else {
                if (isRecordPresent) {
                    val intent = Intent(this, Gallery::class.java)
                    startActivity(intent)
                }
            }


        }

        closeBottomSheet.setOnClickListener {
            backgroundView.visibility = View.GONE
            hideKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }, 100)
            deleteRecord(this, filename)

        }

        doneBtn.setOnClickListener {

            backgroundView.visibility = View.GONE
            hideKeyboard()
            Handler(Looper.getMainLooper()).postDelayed({
                bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
            }, 100)

            val thefilename = textInput.text
            if (filename == thefilename.toString()) {


                val timerD = formatDuration(timerDuration)
                val theDate = getCurrentDate()

                lifecycleScope.launch(Dispatchers.IO) {
                    val dbRecord = DbRecord(filePath, filename, timerD, theDate)
                    database.audioDao().insertAudioRecord(dbRecord)
                }

                Toast.makeText(this, "Recording Saved", Toast.LENGTH_SHORT).show()
            } else {
                val newPath = renameAudioFile(filePath, thefilename.toString())
                val timerD = formatDuration(timerDuration)
                val theDate = getCurrentDate()

                lifecycleScope.launch(Dispatchers.IO) {
                    val dbRecord = DbRecord(newPath, thefilename.toString(), timerD, theDate)
                    database.audioDao().insertAudioRecord(dbRecord)
                }

                Toast.makeText(this, "Recording Saved", Toast.LENGTH_SHORT).show()
            }

            fetchAll()

        }

        fetchAll()

        cancelBtn.isClickable = false
        cancelBtn.setImageResource(R.drawable.disable_cancel_btn)

    }


    private fun fetchAll() {
        lifecycleScope.launch(Dispatchers.IO) {
            val records = database.audioDao().getAllRecords()
            isRecordPresent = records.isNotEmpty()
        }
    }


    private fun formatDuration(duration: Long): String {
        val seconds = (duration / 1000) % 60
        val minutes = (duration / (1000 * 60)) % 60
        val hours = (duration / (1000 * 60 * 60)) % 24

        return if (hours > 0)
            String.format("%02d:%02d:%02d", hours, minutes, seconds)
        else
            String.format("%02d:%02d", minutes, seconds)
    }

    fun getCurrentDate(): String {
        // Get today's date
        val currentDate = LocalDate.now()

        // Define the format: day/month/year
        val formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy")

        // Format the date and return it
        return currentDate.format(formatter)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                permissionGranted = true
            }
        }
    }

    private fun getAudioFilePath(context: Context): String {
        val timestamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
        filename = "AUDIO_$timestamp.m4a"
        val file = File(context.externalCacheDir, filename)
        filePath = file.absolutePath
        return filePath
    }

    fun renameAudioFile(oldPath: String, newName: String): String {
        val oldFile = File(oldPath)

        if (!oldFile.exists()) {
            println("File not found: $oldPath")
            return ""
        }

        // New file path in the same directory
        val newFile = File(oldFile.parent, newName)

        val success = oldFile.renameTo(newFile)
        return if (success) {
            println("Renamed to: ${newFile.absolutePath}")
            newFile.absolutePath
        } else {
            println("Rename failed.")
            ""
        }
    }


    private fun startRecord() {
        recorder = MediaRecorder().apply {
            // configure MediaRecorder
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile(getAudioFilePath(this@MainActivity))
            try {
                prepare()
            } catch (e: IOException) {
                println("Error preparing recorder ${e.message}")
            }
        }

        // Start recording
        recorder?.start()
        timer.start()
        isRecording = true
        recordBtn.setImageResource(R.drawable.pause)
        cancelBtn.isClickable = true
        cancelBtn.setImageResource(R.drawable.stop_record)
        listBtn.setImageResource(R.drawable.checkbtn)

    }

    private fun pauseRecord() {
        recorder?.pause()
        timer.pause()
        isRecording = false
        isPaused = true
        recordBtn.setImageResource(R.drawable.record_btn)

    }

    private fun resumeRecording() {
        recorder?.resume()
        timer.start()
        isRecording = true
        isPaused = false
        recordBtn.setImageResource(R.drawable.pause)
    }

    private fun deleteRecord(context: Context, fileName: String) {
        val file = File(context.externalCacheDir, fileName)
        if (file.exists()) {
            file.delete()
        }
    }


    private fun stopRecord() {
        timerText.text = getString(R.string._00_00_00)
        timer.stopTimer()
        recorder?.apply {
            stop()
            release()
        }
        isRecording = false
        isPaused = false
        cancelBtn.isClickable = false
        cancelBtn.setImageResource(R.drawable.disable_cancel_btn)
        recordBtn.setImageResource(R.drawable.record_btn)
        waveformView.clearWaveFormView()
        listBtn.setImageResource(R.drawable.list_record)
    }

    private fun hideKeyboard() {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        // Use current focus or textInput to hide the keyboard
        val view = textInput
        view.let {
            imm.hideSoftInputFromWindow(it.windowToken, 0)
            it.clearFocus() // Clear focus to prevent keyboard from reappearing
        }
    }

    override fun getDuration(duration: String, theDuration: Long) {
        timerText.text = duration
        timerDuration = theDuration
        recorder?.maxAmplitude?.toFloat()?.let { waveformView.addAmplitude(it) }
    }
}