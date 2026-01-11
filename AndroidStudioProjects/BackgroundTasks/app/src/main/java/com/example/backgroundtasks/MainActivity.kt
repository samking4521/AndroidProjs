package com.example.backgroundtasks

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Looper
import android.util.Log
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton

class MainActivity : AppCompatActivity() {
    private lateinit var launchBtn: MaterialButton
    private lateinit var colorBtn: MaterialButton
    private lateinit var text: TextView
    private lateinit var mainHandler: Handler
    private lateinit var bgThread: HandlerThread
    private lateinit var bgHandler: Handler
    private lateinit var bgRunnable: Runnable
    private lateinit var nextBtn: MaterialButton
    private var count: Int = 0
    private var clicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launchBtn = findViewById(R.id.launchBtn)
        colorBtn = findViewById(R.id.colorBtn)
        text = findViewById(R.id.numberText)
        nextBtn = findViewById(R.id.nextBtn)
        mainHandler = Handler(Looper.getMainLooper()) // UI handler
         bgThread = HandlerThread("MillionCount")
        bgThread.start()

        Log.i("Background Task Started","Action started successfully")
        val bgThreadLooper = bgThread.looper
        bgHandler = Handler(bgThreadLooper)
        bgRunnable = Runnable {
            if(count == 20){
                Log.i("Stopped Counting: ", count.toString())
                bgHandler.removeCallbacks(bgRunnable)
                val actionComplete = Runnable {
                    Toast.makeText(this, "Count Completed", Toast.LENGTH_SHORT).show()
                }
                mainHandler.post(actionComplete)
                count = 0
            }else{
                count += 1
                Log.i("Count: ", count.toString())
                val countRunnable = Runnable {
                    text.text = count.toString()
                }
                mainHandler.post(countRunnable)
                bgHandler.postDelayed(bgRunnable, 1000)
            }

        }

        launchBtn.setOnClickListener{
            launchBackgroundAction()
        }

        nextBtn.setOnClickListener {
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }

        colorBtn.setOnClickListener {
            if(clicked){
                colorBtn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.black)
                clicked = false
                bgHandler.postDelayed(bgRunnable, 1000)
            }else{
                colorBtn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.red)
                clicked = true
                bgHandler.removeCallbacks(bgRunnable)
            }
        }


    }

    private fun launchBackgroundAction(){
        bgHandler.removeCallbacks(bgRunnable)
        count = 0
        bgHandler.postDelayed(bgRunnable, 1000)

    }

    override fun onDestroy() {
        super.onDestroy()
        bgHandler.removeCallbacks(bgRunnable)
    }
}