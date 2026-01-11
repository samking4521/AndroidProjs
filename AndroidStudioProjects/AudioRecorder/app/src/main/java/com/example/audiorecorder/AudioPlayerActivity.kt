package com.example.audiorecorder

import android.media.MediaPlayer
import android.media.PlaybackParams
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.ImageButton
import android.widget.SeekBar
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.chip.Chip
import java.io.IOException
import java.text.DecimalFormat
import java.text.NumberFormat

class AudioPlayerActivity : AppCompatActivity() {
    private lateinit var mediaPlayer: MediaPlayer
    private lateinit var btnPlay: ImageButton
    private lateinit var btnBackward: ImageButton
    private lateinit var btnForward: ImageButton
    private lateinit var speedChip: Chip
    private lateinit var seekBar: SeekBar
    private lateinit var runnable: Runnable
    private lateinit var handler: Handler
    private lateinit var toolbar: MaterialToolbar
    private lateinit var tv_audioText: TextView
    private lateinit var tvTrackProgress: TextView
    private lateinit var tvTrackDuration: TextView

    private var delay = 1000L
    private var jumpValue = 1000
    private var playBackSpeed = 1.0f

    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_audio_player)

        btnBackward = findViewById(R.id.btnBackward)
        btnForward = findViewById(R.id.btnForward)
        btnPlay = findViewById(R.id.btnPlay)
        seekBar = findViewById(R.id.seekbar)
        speedChip = findViewById(R.id.chip)
        toolbar = findViewById(R.id.toolbar)
        tv_audioText = findViewById(R.id.tv_audio_filename)
        tvTrackDuration = findViewById(R.id.tvTrackDuration)
        tvTrackProgress = findViewById(R.id.tvTrackProgress)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }

        val filePath = intent.getStringExtra("filepath")
        val fileName = intent.getStringExtra("filename")

        tv_audioText.text = fileName



        mediaPlayer = MediaPlayer()
        mediaPlayer.apply {
            setDataSource(filePath)
            prepare()
        }

        tvTrackDuration.text = dateFormat(mediaPlayer.duration)

        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            seekBar.progress = mediaPlayer.currentPosition
            tvTrackProgress.text = dateFormat(mediaPlayer.currentPosition)
            handler.postDelayed(runnable, delay)
        }

        btnPlay.setOnClickListener{
            playPausePlayer()
        }

        btnForward.setOnClickListener{
            mediaPlayer.seekTo(mediaPlayer.currentPosition + jumpValue)
            seekBar.progress +=  jumpValue
        }

        btnBackward.setOnClickListener{
            mediaPlayer.seekTo(mediaPlayer.currentPosition - jumpValue)
            seekBar.progress -=  jumpValue
        }

        speedChip.setOnClickListener {
            if(playBackSpeed != 2f)
                playBackSpeed += 0.5f
            else
                playBackSpeed = 0.5f

            mediaPlayer.playbackParams = PlaybackParams().setSpeed(playBackSpeed)
            speedChip.text = "x $playBackSpeed"
        }

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if(fromUser)
                    mediaPlayer.seekTo(progress)
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
            }

        })


        playPausePlayer()
        seekBar.max = mediaPlayer.duration

        mediaPlayer.setOnCompletionListener {
            btnPlay.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_play_circle, theme )
            handler.removeCallbacks(runnable)
        }
    }

    private fun playPausePlayer(){
        if(!mediaPlayer.isPlaying){
            mediaPlayer.start()
            btnPlay.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_pause_circle, theme )

            handler.postDelayed(runnable, delay)
        }else{
            mediaPlayer.pause()
            btnPlay.background = ResourcesCompat.getDrawable(resources, R.drawable.ic_play_circle, theme )
            handler.removeCallbacks(runnable)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        mediaPlayer.stop()
        mediaPlayer.release()
        handler.removeCallbacks(runnable)
    }

    private fun dateFormat(duration: Int): String {
        val d = duration/1000
        val s = d%60
        val m = (d/60 % 60)
        val h = ((d - m*60)/360).toInt()

        val f: NumberFormat = DecimalFormat("00")
        var str = "$m:${f.format(s)}"

        if(h> 0)
            str = "$h:$str"
        return str
    }
}