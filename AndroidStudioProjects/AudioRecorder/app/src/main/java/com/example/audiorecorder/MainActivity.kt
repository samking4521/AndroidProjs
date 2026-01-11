package com.example.audiorecorder

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.text.SimpleDateFormat
import android.media.MediaRecorder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.room.Room
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.ObjectOutputStream
import java.util.Date

const val REQUEST_CODE = 200

class MainActivity : AppCompatActivity(), Timer.OnTimerTickListener {
    private lateinit var btnRecord: ImageButton
    private lateinit var timerText: TextView
    private lateinit var vibrator: Vibrator
    private lateinit var waveformView: WaveformView
    private lateinit var btnList: ImageButton
    private lateinit var btnDone: ImageButton
    private lateinit var btnDelete: ImageButton
    private lateinit var bottomSheet: LinearLayout
    private lateinit var bottomSheetBG: View
    private lateinit var btnCancel: MaterialButton
    private lateinit var btnOk: MaterialButton
    private lateinit var bottomSheetTxtInput: EditText
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<LinearLayout>

    private lateinit var db: AppDatabase
    // create list of permission to be granted
    private var permissions = arrayOf(Manifest.permission.RECORD_AUDIO, Manifest.permission.VIBRATE)
    private var permissionGranted = false

    private lateinit var recorder: MediaRecorder
    private var dirPath = ""
    private var filename = ""
    private var isRecording = false
    private var isPaused = false
    private lateinit var timer: Timer
    private lateinit var amplitude: ArrayList<Float>
    private var duration = ""



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnRecord = findViewById(R.id.btnRecord)
        timerText = findViewById(R.id.tvTimer)
        waveformView = findViewById(R.id.waveFormView)
        btnList = findViewById(R.id.btnList)
        btnDone = findViewById(R.id.btnDone)
        btnDelete = findViewById(R.id.btnDelete)
        btnCancel = findViewById(R.id.btnCancel)
        btnOk = findViewById(R.id.btnOk)
        bottomSheetTxtInput = findViewById(R.id.filenameInput)
        bottomSheetBG = findViewById(R.id.bottomSheetBg)
        bottomSheet = findViewById<LinearLayout>(R.id.bottom_sheet_view)
        bottomSheetBehavior = BottomSheetBehavior.from(bottomSheet)
        bottomSheetBehavior.peekHeight = 0
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED

        // Check whether the permission has been granted
        permissionGranted = ActivityCompat.checkSelfPermission(this, permissions[0]) == PackageManager.PERMISSION_GRANTED

        // request permission, if permission is not granted
        if(!permissionGranted)
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)

        db = Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "audioRecords"
        ).build()

        timer = Timer(this)
        vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        btnRecord.setOnClickListener {
            when{
                isPaused -> resumeRecording()
                isRecording -> pauseRecording()
                else -> startRecording()
            }
              vibrator.vibrate(VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE))
        }

        btnList.setOnClickListener {
           startActivity(Intent(this, GalleryActivity::class.java))
        }

        btnOk.setOnClickListener {
            dismiss()
            save()
        }

        bottomSheetBG.setOnClickListener {
            File("$dirPath$filename.mp3").delete()
            dismiss()
        }

        btnCancel.setOnClickListener{
            File("$dirPath$filename.mp3").delete()
            dismiss()
        }

        btnDone.setOnClickListener {
             stopRecorder()
           Toast.makeText(this, "Record saved", Toast.LENGTH_SHORT).show()
            bottomSheetTxtInput.setText(filename)
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
            bottomSheetBG.visibility = View.VISIBLE

        }

        btnDelete.setOnClickListener {
            stopRecorder()
            File("$dirPath$filename.mp3").delete()
            Toast.makeText(this, "Record deleted", Toast.LENGTH_SHORT).show()

        }

        btnDelete.isClickable = false
        btnDelete.setImageResource(R.drawable.ic_delete_disable)


    }

    private fun save(){
        val newFilename = bottomSheetTxtInput.text.toString()
        if(newFilename != filename){
            val newFile = File("$dirPath$newFilename.mp3")
            File("$dirPath$filename.mp3").renameTo(newFile)
        }

        val filePath = "$dirPath$newFilename.mp3"
        val timestamp = Date().time
        val ampsPath = "$dirPath$newFilename"

        try {
            val fos = FileOutputStream(ampsPath)
            val out = ObjectOutputStream(fos)
            out.writeObject(amplitude)
            fos.close()
            out.close()
        }catch (e: IOException){
             println("Error creating amps file path: ${e.message}")
        }

        val record = AudioRecord(newFilename, filePath, timestamp, duration, ampsPath)

        GlobalScope.launch {
           db.audioRecordDao().insert(record)
        }

    }

    private fun dismiss(){
        bottomSheetBG.visibility = View.GONE
        hideKeyboard(bottomSheetTxtInput)
        Handler(Looper.getMainLooper()).postDelayed({
            bottomSheetBehavior.state = BottomSheetBehavior.STATE_COLLAPSED
        }, 100)

    }


    private fun hideKeyboard(view: View){
        val inm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inm.hideSoftInputFromWindow(view.windowToken, 0)
    }

    // update permissionGranted status depending on user action
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if(requestCode == REQUEST_CODE){
            permissionGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED
        }
    }
    private fun pauseRecording(){
        recorder.pause()
        isRecording = false
        isPaused = true
        btnRecord.setImageResource(R.drawable.ic_record)

        timer.pause()
    }

    private fun resumeRecording(){
        recorder.resume()
        isPaused = false
        isRecording = true
        btnRecord.setImageResource(R.drawable.ic_pause)

        timer.start()

    }

    private fun startRecording(){
        if(!permissionGranted){
            ActivityCompat.requestPermissions(this, permissions, REQUEST_CODE)
            return
        }
        //start recording if permission is granted

        recorder = MediaRecorder()
        dirPath = "${externalCacheDir?.absolutePath}/"
        val simpleDateFormat = SimpleDateFormat("yyyy.MM.DD_hh.mm.ss")
        val date = simpleDateFormat.format(Date())
        filename = "audio_record_$date"

        recorder.apply {
            setAudioSource(MediaRecorder.AudioSource.MIC)
            setOutputFormat(MediaRecorder.OutputFormat.MPEG_4)
            setAudioEncoder(MediaRecorder.AudioEncoder.AAC)
            setOutputFile("$dirPath$filename.mp3")

            try {
                prepare()
            }catch (_: IOException) {}

            start()
        }

        btnRecord.setImageResource(R.drawable.ic_pause)
        isRecording = true
        isPaused = false

        timer.start()

        btnDelete.isClickable = true
        btnDelete.setImageResource(R.drawable.ic_delete)

        btnList.visibility = View.GONE
        btnDone.visibility = View.VISIBLE

    }

    private fun stopRecorder(){
        timer.stop()
        recorder.apply {
            stop()
            release()
        }

        isPaused = false
        isRecording = false

        btnList.visibility = View.VISIBLE
        btnDone.visibility = View.GONE

        btnDelete.isClickable = false
        btnDelete.setImageResource(R.drawable.ic_delete_disable)

        btnRecord.setImageResource(R.drawable.ic_record)

         timerText.text = getString(R.string.tv_timer_text)
         amplitude = waveformView.clear()

    }

    override fun onTimerTick(duration: String) {
         timerText.text = duration
         this.duration = duration.dropLast(3)
        waveformView.addAmplitude(recorder.maxAmplitude.toFloat())

    }


}

