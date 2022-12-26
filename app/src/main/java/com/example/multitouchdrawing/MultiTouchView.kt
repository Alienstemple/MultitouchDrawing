package com.example.multitouchdrawing

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View

class MultiTouchView(context: Context, attrs: AttributeSet?, defStyleAttrs: Int, defStyleRes: Int) :
    View(context, attrs, defStyleAttrs, defStyleRes) {

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val action = event.actionMasked

        return when(action) {
            MotionEvent.ACTION_DOWN -> true
            MotionEvent.ACTION_POINTER_DOWN -> true
            MotionEvent.ACTION_MOVE -> true
            MotionEvent.ACTION_POINTER_UP -> true
            MotionEvent.ACTION_UP -> true
            else -> {super.onTouchEvent(event)}
        }
    }
}