package com.example.merecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.merecyclerview.Utils.Constants

class SecondActivity : AppCompatActivity() {
    private lateinit var textOne: TextView
    private lateinit var textTwo: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        textOne = findViewById(R.id.textView)
        textTwo = findViewById(R.id.textView2)

        val data = intent.extras
        data?.let {
            textOne.text = data.getString(Constants.titleVal)
            textTwo.text = data.getString(Constants.descVal)
        }


    }
}