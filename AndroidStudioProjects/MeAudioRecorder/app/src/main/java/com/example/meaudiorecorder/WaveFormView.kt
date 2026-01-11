package com.example.meaudiorecorder

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View

class WaveFormView(context: Context?, attrs: AttributeSet?) : View(context, attrs) {
    private var paint = Paint()
    private var amplitude = ArrayList<Float>()
    private var spikes = ArrayList<RectF>()
    private var w = 9f
    private var radius = 6f
    private var sw = 0
    private var d = 6f
    private var sh = 400f
    private var maxSpikes = 0


    init {
        paint.color = Color.rgb(244, 81, 30)
        sw = resources.displayMetrics.widthPixels
        maxSpikes = (sw / (w + d)).toInt()
    }

    fun addAmplitude(amp: Float) {
        val norm = (amp.toInt() / 7).coerceAtMost(400).toFloat()
        amplitude.add(norm)
        spikes.clear()

        val amps = amplitude.takeLast(maxSpikes)
        for (i in amps.indices) {
            val left = sw - i * (w + d)
            val right = left + w
            val top = sh / 2 - amps[i] / 2
            val bottom = top + amps[i]

            spikes.add(RectF(left, top, right, bottom))
        }

        invalidate()


    }

    fun clearWaveFormView(){
        spikes.clear()
        amplitude.clear()
        invalidate()
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        spikes.forEach {
            canvas.drawRoundRect(it, radius, w, paint)
        }
    }
}