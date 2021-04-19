package com.moqi.systemview.drag

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import com.moqi.systemview.R
import com.moqi.systemview.databinding.ActivityMoveBinding

class MoveActivity : AppCompatActivity() {
    private lateinit var vb: ActivityMoveBinding

    private var lastX = 0f
    private var lastY = 0f

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        vb = ActivityMoveBinding.inflate(layoutInflater)
        setContentView(vb.root)

        vb.viewMask.setOnTouchListener { v, event ->
            move(event)
            return@setOnTouchListener true
        }
    }

    private fun move(event: MotionEvent) {
        when(event.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.x - lastX
                val dy = event.y - lastY
                performMove(dx, dy)
                lastX = event.x
                lastY = event.y
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                stopMove()
            }
        }
    }

    private fun performMove(dx: Float, dy: Float) {
        val tx = vb.ivItem.translationX
        val ty = vb.ivItem.translationY
        vb.ivItem.translationX = tx + dx
        vb.ivItem.translationY = ty + dy
    }

    private fun stopMove() {

    }
}