package com.example.backgroundtasks

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.google.android.material.button.MaterialButton
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.concurrent.CancellationException

class SecondActivity : AppCompatActivity() {
    private lateinit var launchBtn: MaterialButton
    private lateinit var colorBtn: MaterialButton
    private lateinit var text: TextView
    private lateinit var backBtn: MaterialButton
    private var count = 0
    private var job: Job? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        launchBtn = findViewById(R.id.launchBtn)
        colorBtn = findViewById(R.id.colorBtn)
        text = findViewById(R.id.numberText)
        backBtn = findViewById(R.id.backBtn)


        colorBtn.setOnClickListener {
            if(job?.isActive == true){
                colorBtn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.red)
                pauseCounting()
            }else{
                colorBtn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.black)
                job = lifecycleScope.launch {
                    startBackgroundTask()
                    withContext(Dispatchers.Main){
                        Toast.makeText(this@SecondActivity, "Count completed", Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

        backBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        launchBtn.setOnClickListener {
               stopCounting()
                job = lifecycleScope.launch {
                    try {
                        startBackgroundTask()

                            Toast.makeText(this@SecondActivity, "Count completed", Toast.LENGTH_SHORT).show()

                    }catch (e: Exception){
                        Toast.makeText(this@SecondActivity, e.message, Toast.LENGTH_SHORT).show()
                    }

                }
            }
        }

    private fun stopCounting (){
            if (job?.isActive == true){
                job?.cancel(CancellationException("Count stopped"))
            }
            job = null
            count = 0
        colorBtn.backgroundTintList = ContextCompat.getColorStateList(this, R.color.black)

        }


    private fun pauseCounting (){
            if(job?.isActive == true){
                job?.cancel(CancellationException("Count paused"))
            }
            job = null
    }

    private suspend fun startBackgroundTask(){
           while (count < 20){
               delay(1000)
               count += 1

                   text.text = count.toString()


           }
    }


}