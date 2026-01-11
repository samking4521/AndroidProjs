package com.example.mydrawingapp

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.graphics.createBitmap
import androidx.core.graphics.toColorInt

class DrawingView(context: Context, attrs: AttributeSet): View(context, attrs) {
    private lateinit var bitmap: Bitmap
    private lateinit var paint: Paint
    private lateinit var path: FingerPath
    private lateinit var bitmapCanvas: Canvas
    private var brushSize = 20f
    private var color = "#FF000000".toColorInt()
    private var pathList = mutableListOf<FingerPath>()
    private var undo = false


    init {
        setUpDrawingView()
    }

    private fun setUpDrawingView(){
        paint = Paint().apply {
            color = "#FF000000".toColorInt()
            path = FingerPath(color, brushSize)
            style = Paint.Style.STROKE
            strokeWidth = brushSize
            strokeJoin = Paint.Join.ROUND
            strokeCap = Paint.Cap.ROUND
        }
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        bitmap = createBitmap(w, h, Bitmap.Config.ARGB_8888)
        bitmapCanvas = Canvas(bitmap)
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, paint)
        if(undo){
            for(path in pathList){
                paint.color = path.color
                paint.strokeWidth = path.brushSize
                canvas.drawPath(path, paint)
            }
            undo = false
        }else {
            canvas.drawPath(path, paint)
            for(path in pathList){
                paint.color = path.color
                paint.strokeWidth = path.brushSize
                canvas.drawPath(path, paint)
            }
        }

//            pathList.forEach{
//                paint.color = it.color
//                paint.strokeWidth = it.brushSize
//                canvas.drawPath(it, paint)
//            }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
          val touchX = event.x
          val touchY = event.y

          when(event.action){
              MotionEvent.ACTION_UP -> {
//                    paint.color = path.color
//                    paint.strokeWidth = path.brushSize
//                    bitmapCanvas.drawPath(path, paint)
              }

              MotionEvent.ACTION_DOWN -> {
                  path = FingerPath(color, brushSize)
                  path.color = color
                  path.brushSize = brushSize
                  path.moveTo(touchX, touchY)
                  pathList.add(path)
              }

              MotionEvent.ACTION_MOVE -> {
                    path.lineTo(touchX, touchY)
              }

              else -> return false

          }

        invalidate()

        return true
    }

    fun setColor(newColor: Any){
        if(newColor is String){
            color = newColor.toColorInt()
            paint.color = color
        }else {
            color = newColor as Int
            paint.color = color
        }

    }

    fun undoPath(){
//        if(pathList.isNotEmpty()){
//            pathList.removeAt(pathList.lastIndex)
//            invalidate()
//        }
        undo = true
        if(pathList.isNotEmpty()){
            pathList.removeAt(pathList.lastIndex)
            invalidate()

        }
    }

    fun changeBrushSize(size: Float){
        brushSize = size
        paint.strokeWidth = brushSize
    }

    inner class FingerPath(var color: Int, var brushSize: Float): Path()

}