package com.example.meaudiorecorder

import android.os.Handler
import android.os.Looper

class Timer(listener: OnSetTimeListener) {
    interface OnSetTimeListener {
        fun getDuration(duration: String, theDuration: Long)
    }

    private val looper = Looper.getMainLooper()
    private var handler: Handler = Handler(looper)
    private lateinit var runnable: Runnable
    private var duration = 0L
    private var delay = 100L

    init {
         runnable = Runnable {
            duration += delay
             handler.postDelayed(runnable, delay) // Infinite loop
            listener.getDuration(formatDuration(), duration)


        }
    }

    fun start(){
        duration = 0
        handler.postDelayed(runnable, delay)
    }

    fun stopTimer(){
        handler.removeCallbacks(runnable)

    }

    fun pause(){
        handler.removeCallbacks(runnable)
    }


    private fun formatDuration(): String {
        val millis = duration % 1000
        val seconds = (duration / 1000) % 60
        val minutes = (duration / (1000 * 60)) % 60
        val hours = (duration / (1000 * 60 * 60)) % 24

        return if (hours > 0)
            String.format("%02d:%02d:%02d:%02d", hours, minutes, seconds, millis / 10)
        else
            String.format("%02d:%02d:%02d", minutes, seconds, millis / 10)


    }
}