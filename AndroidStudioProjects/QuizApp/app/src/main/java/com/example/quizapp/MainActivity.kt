package com.example.quizapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.quizapp.ui.QuestionActivity
import com.example.quizapp.utils.Constants
import com.google.android.material.button.MaterialButton
import com.google.android.material.textfield.TextInputEditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textInput: EditText = findViewById(R.id.nameBox)
        val startButton: Button = findViewById(R.id.startButton)

        startButton.setOnClickListener {
            if(textInput.text.isNotEmpty()) {
                Intent(this@MainActivity, QuestionActivity::class.java).also {
                    it.putExtra(Constants.USER_NAME, textInput.text.toString())
                    startActivity(it)
                    finish()
                }
            }else{
                   Toast.makeText(this@MainActivity, "Enter your name", Toast.LENGTH_LONG).show()
                }
            }
        }
}