package com.example.multitouchdrawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MultiTouchView(context: Context, attrs: AttributeSet?, defStyleAttrs: Int, defStyleRes: Int) :
    View(context, attrs, defStyleAttrs, defStyleRes) {

    private val mCircles: ArrayList<Circle> = arrayListOf()
    private val mPaint = Paint()

    init {
        setupPaint()
    }

    override fun onDraw(canvas: Canvas?) {
        for (circle in mCircles) {
            canvas?.drawCircle(circle.origin.x, circle.origin.y, circle.radius, mPaint)
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked

        var currentCircle: Circle

        return when(action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_POINTER_DOWN -> {
                val point = PointF(event.x, event.y)
                true
            }
            MotionEvent.ACTION_MOVE -> true
            MotionEvent.ACTION_POINTER_UP -> true
            MotionEvent.ACTION_UP -> true
            else -> {super.onTouchEvent(event)}
        }
    }

    private fun setupPaint() {
        mPaint.flags = Paint.ANTI_ALIAS_FLAG
        mPaint.style = Paint.Style.STROKE
        mPaint.strokeWidth = STROKE_WIDTH
    }

    companion object {
        const val STROKE_WIDTH = 10f
    }
}