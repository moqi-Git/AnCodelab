package com.moqi.systemview.view

import android.content.Context
import android.graphics.Canvas
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.view.View

class BaseView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    //Draw -

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        Log.e("asdfg", "view onMeasured: w=$measuredWidth, h=$measuredHeight")
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        Log.e("asdfg", "view onLayout:")
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        Log.e("asdfg", "view onDraw:")
    }

    //Event -

    override fun dispatchTouchEvent(event: MotionEvent?): Boolean {
        Log.e("asdfg", "view dispatchTouchEvent: e=${event?.actionMasked}")
        return super.dispatchTouchEvent(event)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("asdfg", "view onTouchEvent: e=${event?.actionMasked}")
        return super.onTouchEvent(event)
    }


    //
}