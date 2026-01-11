package com.example.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.result.contract.ActivityResultContracts

class MainActivity : AppCompatActivity() {
    private lateinit var button: Button
    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.button)
        textView = findViewById(R.id.textView)

        val getResult = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            if(it.resultCode == Constants.SECOND_INTENT_DATA){
                val message = it.data?.getStringExtra(Constants.INTENT_MESSAGE2_KEY)
                textView.text = message

            }
        }

        button.setOnClickListener {
            val intent = Intent(this@MainActivity, SecondActivity::class.java)
            intent.putExtra(Constants.INTENT_MESSAGE_KEY, "This data is from activity 1")
            intent.putExtra(Constants.INTENT_MESSAGE2_KEY, "How are you doing?")
            intent.putExtra(Constants.INTENT_MESSAGE3_KEY, 3.142)
            getResult.launch(intent)
//            startActivity(intent)
//            Intent(this@MainActivity, SecondActivity::class.java).also {
//                startActivity(it)
//            }
        }
    }
}