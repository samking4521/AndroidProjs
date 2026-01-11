package com.example.audioplayerapp

import android.os.Bundle
import android.widget.ImageButton
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    private lateinit var playButton: ImageButton
    private lateinit var cancelAudioButton: ImageButton
    private lateinit var listButton: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        playButton = findViewById(R.id.playAudioBtn)
        cancelAudioButton = findViewById(R.id.cancelButton)
        listButton = findViewById(R.id.listButton)

        playButton.setOnClickListener {
            playButton.setImageResource(R.drawable.ic_pause)
        }

        cancelAudioButton.isClickable = false
    }


}