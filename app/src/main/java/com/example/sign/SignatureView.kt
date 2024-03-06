package com.example.sign

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

class SignatureView(context: Context) : View(context) {
    // Initialize properties for stroke width, stroke color, and background color
    private var strokeWidth = 10f
    private var strokeColor = Color.BLACK
    private var background = Color.WHITE

    // Initialize Paint object for drawing
    private val paint = Paint()

    // Initialize Path object to store the drawing path
    private val path = Path()

    // Store last touch coordinates
    private var lastTouchX = 0f
    private var lastTouchY = 0f

    init {
        // Set layout parameters to match parent
        layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )

        // Configure paint properties
        paint.isAntiAlias = true
        paint.color = strokeColor
        paint.style = Paint.Style.STROKE
        paint.strokeJoin = Paint.Join.ROUND
        paint.strokeWidth = strokeWidth
    }

    // Method to set stroke width
    fun setStrokeWidth() {
        paint.strokeWidth = strokeWidth
    }

    // Method to set stroke color
    fun setStrokeColor() {
        paint.color = strokeColor
    }

    // Method to set background color
    fun setBackground() {
        this.background = background
    }

    // Handle touch events
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                // When touch is initiated, move to the touch point
                path.moveTo(event.x, event.y)
                lastTouchX = event.x
                lastTouchY = event.y
                return true
            }

            MotionEvent.ACTION_MOVE, MotionEvent.ACTION_UP -> {
                // When touch moves or is released, draw a line to the current touch point
                val historySize = event.historySize
                for (i in 0 until historySize) {
                    val historicalX = event.getHistoricalX(i)
                    val historicalY = event.getHistoricalY(i)
                    path.lineTo(historicalX, historicalY)
                }
                path.lineTo(event.x, event.y)
            }

            else -> {
                return false
            }
        }
        // Request redraw
        invalidate()
        lastTouchX = event.x
        lastTouchY = event.y
        return true
    }

    // Draw the path on the canvas
    override fun onDraw(canvas: Canvas) {
        // Draw background color
        canvas.drawColor(background)
        // Draw the path using the paint object
        canvas.drawPath(path, paint)
    }

    // Method to clear the drawing
    fun clear() {
        // Reset the path and invalidate to clear the view
        path.reset()
        invalidate()
    }
}
