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

class DrawingView(context: Context, attrs: AttributeSet) : View(context, attrs)  {
    // drawing path
    private lateinit var drawPath: FingerPath
    // defines what to draw
    private lateinit var canvasPaint: Paint
    private lateinit var canvas: Canvas
    private lateinit var canvasBitmap: Bitmap
    // defines how to draw
    private lateinit var drawPaint: Paint
    private var color = Color.BLACK

    private var brushSize: Float = 0.toFloat()
    private val paths = mutableListOf<FingerPath>()


    init {
        setUpDrawing()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        canvas = Canvas(canvasBitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(canvasBitmap, 0f, 0f, drawPaint)
        for (path in paths){
            drawPaint.strokeWidth = path.brushThickness
            drawPaint.color = path.color
            canvas.drawPath(path, drawPaint)
        }
        if(!drawPath.isEmpty){
            drawPaint.strokeWidth = drawPath.brushThickness
            drawPaint.color = drawPath.color
            canvas.drawPath(drawPath, drawPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX = event?.x //touch event of X coordinate
        val touchY = event?.y //touch event of Y coordinate

        when(event?.action){
            // This event will be fired when the user put finger on the screen
            MotionEvent.ACTION_DOWN -> {
                drawPath.color = color
                drawPath.brushThickness = brushSize

                drawPath.reset() // resetting path before we set initial point
                drawPath.moveTo(touchX!!, touchY!!)
            }
            // The event will be fired when the user starts to move it's finger, this will
            // be fired continually until user move finger away from the screen
            MotionEvent.ACTION_MOVE -> {
                drawPath.lineTo(touchX!!, touchY!!)
            }
            // This event will be fired when the user move up the finger away from the screen
            MotionEvent.ACTION_UP -> {
                drawPath = FingerPath(color, brushSize)
                paths.add(drawPath)
            }
            else -> return false
        }

        invalidate() // refreshing the layout to reflect the drawing changes
        return true

    }

    private fun setUpDrawing(){
        drawPaint = Paint()
        drawPath = FingerPath(color, brushSize)
        drawPaint.color = color
        drawPaint.style = Paint.Style.STROKE
        drawPaint.strokeJoin = Paint.Join.ROUND
        drawPaint.strokeCap = Paint.Cap.ROUND

        canvasPaint = Paint(Paint.DITHER_FLAG)
        brushSize = 20.toFloat()
    }

    fun changeBrushSize(newSize: Float){
        brushSize = TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            newSize, resources.displayMetrics
        )

        drawPaint.strokeWidth = brushSize
    }


    internal inner class FingerPath(var color: Int, var brushThickness: Float) : Path()

}