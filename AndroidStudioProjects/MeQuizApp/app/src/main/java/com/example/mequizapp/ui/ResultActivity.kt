package com.example.mequizapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.mequizapp.Constants
import com.example.mequizapp.MainActivity
import com.example.mequizapp.R

class ResultActivity : AppCompatActivity() {
    private lateinit var userName: TextView
    private lateinit var resultMsg: TextView
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        userName = findViewById(R.id.playerName)
        resultMsg = findViewById(R.id.playerResult)
        finishButton = findViewById(R.id.finishButton)

        val name = intent.getStringExtra(Constants.PLAYER_NAME)
        val result = intent.getStringExtra(Constants.PLAYER_RESULT)

        userName.text = name
        resultMsg.text = result

        finishButton.setOnClickListener{
            Intent(this@ResultActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}