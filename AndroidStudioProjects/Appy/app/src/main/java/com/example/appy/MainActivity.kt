package com.example.appy

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView
    private lateinit var editText: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)
        editText = findViewById(R.id.editTextText)

        button.text = getString(R.string.textview)

        button.setOnClickListener {
            val textVal = editText.text.toString()
            if(textVal.isNotEmpty()){
                textView.visibility = View.VISIBLE
                textView.text = textVal
                textView.setTextColor(Color.BLACK)
                editText.text = null
            }else{
                textView.visibility = View.VISIBLE
                textView.text = getString(R.string.textview_dynamic)
                textView.setTextColor(Color.RED)
            }
        }

    }
}