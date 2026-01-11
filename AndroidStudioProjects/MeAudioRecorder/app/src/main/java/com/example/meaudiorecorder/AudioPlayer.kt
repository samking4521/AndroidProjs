package com.example.meaudiorecorder

import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.appbar.MaterialToolbar

class AudioPlayer : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var FileName: TextView
    private lateinit var SeekBar: SeekBar
    private lateinit var startDuration: TextView
    private lateinit var endDuration: TextView
    private lateinit var forwardBtn: ImageButton
    private lateinit var speedCont: LinearLayout
    private lateinit var speedText: TextView
    private lateinit var backwardBtn: ImageButton
    private lateinit var playBtn: ImageButton
    private lateinit var toolbar: MaterialToolbar
    private var filePath: String? = null
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private var isPlaying = true
    private var clickCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_player)

        // Access View Widgets
        FileName = findViewById(R.id.audioName)
        SeekBar = findViewById(R.id.seekbar)
        startDuration = findViewById(R.id.startDuration)
        endDuration = findViewById(R.id.endDuration)
        forwardBtn = findViewById(R.id.forwardBtn)
        backwardBtn = findViewById(R.id.backwardBtn)
        playBtn = findViewById(R.id.playBtn)
        speedCont = findViewById(R.id.speedCont)
        speedText = findViewById(R.id.speed)
        toolbar = findViewById(R.id.topBar)
        setSupportActionBar(toolbar)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }


        // Get data from other activity
        val theFileName = intent.getStringExtra("fileName")
        FileName.text = theFileName
        filePath = intent.getStringExtra("filePath").toString()
        val duration = intent.getIntExtra("duration", 0)


        // Configure mediaPlayer and start playing
        mediaPlayer = MediaPlayer()
        mediaPlayer.setDataSource(filePath)
        mediaPlayer.prepare() // or prepareAsync() if streaming
        mediaPlayer.start()

        endDuration.text = formatDuration(mediaPlayer.duration)
        SeekBar.max = mediaPlayer.duration

        // Start background actions to start playing
        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            startDuration.text = formatDuration(mediaPlayer.currentPosition)
            SeekBar.progress = mediaPlayer.currentPosition
            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 0)


        // Stop background updates on audio play completion
        mediaPlayer.setOnCompletionListener {
            handler.removeCallbacks(runnable)
            playBtn.setImageResource(R.drawable.play)
            isPlaying = false
            SeekBar.progress = 0
            startDuration.text = formatDuration(0)
        }

        SeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    mediaPlayer.seekTo(progress) // Jump to selected position

                }
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {
                // optional - pause updating when user starts touching
            }

            override fun onStopTrackingTouch(sb: SeekBar?) {
                // optional - resume updating
            }


        })

        playBtn.setOnClickListener {
            if (isPlaying) {
                playBtn.setImageResource(R.drawable.play)
                isPlaying = false
                mediaPlayer.pause()
                handler.removeCallbacks(runnable)

            } else {
                playBtn.setImageResource(R.drawable.ic_pause_audio)
                isPlaying = true
                mediaPlayer.start()
                handler.postDelayed(runnable, 1000)
            }

        }

        speedCont.setOnClickListener {
                when (clickCount) {
                     0 -> {
                         clickCount = 1
                         speedText.text = "1.0"
                         val params = mediaPlayer.playbackParams
                         params.speed = 1.0f
                         mediaPlayer.playbackParams = params
                    }
                    1 -> {
                        clickCount = 2
                        speedText.text = "1.5"
                        val params = mediaPlayer.playbackParams
                        params.speed = 1.5f
                        mediaPlayer.playbackParams = params
                    }

                    2 -> {
                        clickCount = 0
                        speedText.text = "2.0"
                        val params = mediaPlayer.playbackParams
                        params.speed = 2.0f
                        mediaPlayer.playbackParams = params
                    }
                }
        }

        playBtn.setImageResource(R.drawable.ic_pause_audio)


        forwardBtn.setOnClickListener {
            if (isPlaying) {
                val skipVal = mediaPlayer.currentPosition + 1000
                SeekBar.progress = skipVal
                mediaPlayer.seekTo(skipVal)
                startDuration.text = formatDuration(skipVal)
            }
        }

        backwardBtn.setOnClickListener {
            if (isPlaying) {
                val skipVal = mediaPlayer.currentPosition - 1000
                SeekBar.progress = skipVal
                mediaPlayer.seekTo(skipVal)
                startDuration.text = formatDuration(skipVal)
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        handler.removeCallbacks(runnable)
        mediaPlayer.stop()
        mediaPlayer.release()
    }


    private fun formatDuration(duration: Int): String {
        val seconds = (duration / 1000) % 60
        val minutes = (duration / (1000 * 60)) % 60
        val hours = (duration / (1000 * 60 * 60)) % 24

        return if (hours > 0)
            String.format("%d:%02d:%02d", hours, minutes, seconds)
        else
            String.format("%d:%02d", minutes, seconds)
    }

}

