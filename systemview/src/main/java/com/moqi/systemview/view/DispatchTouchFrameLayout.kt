package com.moqi.systemview.view

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.MotionEvent
import android.widget.FrameLayout
import kotlin.math.abs

/**
 *
 * created by reol at 2/7/21
 */
class DispatchTouchFrameLayout @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private var mInitialTouchX = 0f
    private var mInitialTouchY = 0f

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        Log.e("asdfg", "dispatchTouchEvent ${ev?.action}")
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                mInitialTouchX = ev.x
                mInitialTouchY = ev.y
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = ev.x - mInitialTouchX
                val dy = ev.y - mInitialTouchY

                var hasScrollView = false
                for (i in 0 until childCount) {
                    val child = getChildAt(i)
                    if (child.canScrollHorizontally(-1) && dx > 0) {
                        hasScrollView = true
                    }
                    if (child.canScrollHorizontally(1) && dx < 0) {
                        hasScrollView = true
                    }
                }

                val r = abs(dy) / abs(dx)
                if (r < 0.6f && hasScrollView) {
                    requestDisallowInterceptTouchEvent(true)
                }
            }

            MotionEvent.ACTION_UP -> {
                requestDisallowInterceptTouchEvent(false)
            }
            MotionEvent.ACTION_CANCEL -> {
                requestDisallowInterceptTouchEvent(false)
            }
        }

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