package com.example.multitouchdrawing

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.util.Log
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View

class MultiTouchView(context: Context, attrs: AttributeSet? = null) :
    View(context, attrs) {

    private val mCircles: HashMap<Int, Circle> = HashMap()
    private val mPaint = Paint(Color.BLACK)
    private val circleRadius = 100f

    init {
        setupPaint()
    }

    override fun onDraw(canvas: Canvas?) {
        for (i in mCircles.keys) {
            val curCircle = mCircles[i]
            canvas?.drawCircle(curCircle!!.origin.x, curCircle.origin.y, curCircle.radius, mPaint)  // TODO wrap to let
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked
        val pointerIndex = event.actionIndex
        val pointerId = event.getPointerId(pointerIndex)
        val point = PointF(event.x, event.y)


        var currentCircle: Circle

        return when(action) {
            MotionEvent.ACTION_DOWN,
            MotionEvent.ACTION_POINTER_DOWN -> {

                currentCircle = Circle(point, circleRadius)
                mCircles[pointerId] = currentCircle
                Log.d("MultLog", "Draw circle, index $pointerIndex, id $pointerId, x = ${point.x}, y = ${point.y}")
                true
            }
            MotionEvent.ACTION_MOVE -> {
//                for (i in 0..event.pointerCount) {
//                    val id = event.getPointerId(i)
//                    currentCircle = mCircles[id]!!
//                    currentCircle.origin = PointF(event.x, event.y)
//                }
                invalidate()
                true
            }
            MotionEvent.ACTION_POINTER_UP -> {
                mCircles.remove(pointerId)
                Log.d("MultLog", "Removed, id $pointerId, x = ${point.x}, y = ${point.y}")
                true
            }
            MotionEvent.ACTION_UP,
            MotionEvent.ACTION_CANCEL -> {
                mCircles.clear()
                true
            }
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