package com.example.mequizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.example.mequizapp.ui.QuestionsActivity
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    private lateinit var textInputEditText: TextInputEditText
    private lateinit var startButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textInputEditText = findViewById(R.id.nameData)
        startButton = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            val name = textInputEditText.text.toString()
            if(name.isNotEmpty()){
                Intent(this@MainActivity, QuestionsActivity::class.java).also {
                    it.putExtra(Constants.PLAYER_NAME, name)
                    startActivity(it)
                    finish()
                }
            }else{
                Toast.makeText(this@MainActivity, "Enter your name", Toast.LENGTH_SHORT).show()
            }

        }
    }
}