package com.example.quizapp.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.quizapp.MainActivity
import com.example.quizapp.R
import com.example.quizapp.utils.Constants

class ResultActivity : AppCompatActivity() {
    private lateinit var playerName: TextView
    private lateinit var playerResult: TextView
    private lateinit var finishButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        playerName = findViewById(R.id.playerName)
        playerResult = findViewById(R.id.playerResult)
        finishButton = findViewById(R.id.finishButton)

        val playerNameVal = intent.getStringExtra(Constants.USER_NAME)
        val playerScoreVal = intent.getIntExtra(Constants.SCORE, 0)
        val totalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        playerName.text = playerNameVal
        playerResult.text = "You scored $playerScoreVal out of $totalQuestions questions"

        finishButton.setOnClickListener {
            Intent(this@ResultActivity, MainActivity::class.java).also {
                startActivity(it)
            }
        }



    }
}