package com.moqi.systemview.view

import android.content.Context
import android.util.AttributeSet
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

    private var downX = 0f
    private var downY = 0f

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        when (ev?.action) {
            MotionEvent.ACTION_DOWN -> {
                // 优化判断方式
                downX = ev.x
                downY = ev.y
//                requestDisallowInterceptTouchEvent(true)
            }

            MotionEvent.ACTION_MOVE -> {
                val dx = ev.x - downX

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

                val dy = ev.y - downY
                val r = abs(dy) / abs(dx)
                if (r < 0.45f && hasScrollView) {
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
}