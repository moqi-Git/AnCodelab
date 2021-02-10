package com.github.moqigit.tabbarlab.base

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout

/**
 *
 * created by reol at 2/9/21
 */
class LogFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("asdfg", "dispatchTouchEvent ${ev?.action}")
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("asdfg", "onInterceptTouchEvent ${ev?.action}")
        return super.onInterceptTouchEvent(ev)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        Log.e("asdfg", "onTouchEvent ${event?.action}")
        return super.onTouchEvent(event)
    }

}