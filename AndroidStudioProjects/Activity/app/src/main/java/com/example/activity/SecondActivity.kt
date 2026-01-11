package com.example.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var Text: TextView
    private lateinit var Button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        val data = intent.extras

        Text = findViewById(R.id.textView2)
        Button = findViewById(R.id.button2)

        Button.setOnClickListener {
            val intent = intent
            intent.putExtra(Constants.INTENT_MESSAGE2_KEY, "Hello from activity 2")
            setResult(Constants.SECOND_INTENT_DATA, intent)
            finish()
        }
        data?.let {
            val message = data.getString(Constants.INTENT_MESSAGE_KEY)
            val message2 = data.getString(Constants.INTENT_MESSAGE2_KEY)
            val message3 = data.getDouble(Constants.INTENT_MESSAGE3_KEY)

            Text.text = message + "\n" + message2 + "\n" + message3
        }

    }
}