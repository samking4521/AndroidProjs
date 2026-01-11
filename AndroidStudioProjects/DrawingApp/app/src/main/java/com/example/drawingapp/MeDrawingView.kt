package com.example.drawingapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.toColorInt
import androidx.core.graphics.createBitmap

class MyDrawingView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private lateinit var canvasBitmap: Bitmap
    private lateinit var drawPaint: Paint
    private lateinit var canvasPaint: Paint
    private lateinit var canvas: Canvas
    private lateinit var drawPath: FingerPath
    private var color = Color.BLACK
    private var brushSize: Float = 0.toFloat()
    private var pathList = mutableListOf<FingerPath>()

    init {
        setDrawingView()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // create a new bitmap to fit the new orientation
        canvasBitmap = createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap, 0f, 0f, drawPaint)
        for (path in pathList) {
            drawPaint.color = path.color
            drawPaint.strokeWidth = path.brushThickness
            canvas.drawPath(path, drawPaint)
        }

        if (!drawPath.isEmpty) {
            drawPaint.color = drawPath.color
            drawPaint.strokeWidth = drawPath.brushThickness

            canvas.drawPath(drawPath, drawPaint)
            if (pathList.isEmpty()) {
                pathList.add(drawPath)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x
        val touchY = event?.y

        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                drawPath.color = color
                drawPath.brushThickness = brushSize
                drawPath.reset()
                drawPath.moveTo(touchX!!, touchY!!)
            }

            MotionEvent.ACTION_MOVE -> {
                drawPath.lineTo(touchX!!, touchY!!)
            }

            MotionEvent.ACTION_UP -> {
                drawPath = FingerPath(color, brushSize)
                pathList.add(drawPath)

            }

            else -> return false

        }

        invalidate()
        return true
    }

    private fun setDrawingView() {
        drawPaint = Paint()
        drawPath = FingerPath(color, brushSize)
        drawPaint.style = Paint.Style.STROKE
        drawPaint.color = color
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND
        brushSize = 20.toFloat()
        canvasPaint = Paint(Paint.DITHER_FLAG)

    }

    fun changeBrushSize(newSize: Float) {
        brushSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            newSize,
            resources.displayMetrics
        )

        drawPaint.strokeWidth = brushSize

    }

    fun setColor(newColor: Any) {
        if (newColor is String) {
            color = newColor.toColorInt()
            drawPaint.color = color
        } else {
            color = newColor as Int
            drawPaint.color = color
        }

    }

    fun undoPath() {
        if (pathList.size > 0) {
            pathList.remove(pathList[pathList.size - 1])
            invalidate()
        }
    }

    internal inner class FingerPath(var color: Int, var brushThickness: Float) : Path()

}