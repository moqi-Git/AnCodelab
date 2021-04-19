package com.moqi.systemview.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View

class CropMaskView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val maskPaint = Paint().apply {
        color = Color.parseColor("#80000000")
        isAntiAlias = true
    }
    private val strokePaint = Paint().apply {
        color = Color.WHITE
        isAntiAlias = true
        strokeWidth = 3f
        style = Paint.Style.STROKE
    }
    private val linePaint = Paint().apply {
        color = Color.parseColor("#07c160")
        isAntiAlias = true
        strokeWidth = 3f
    }

    private val topAlign = 80f
    private val leftAlign = 60f
    private val bottomAlign = 80f
    private val rightAlign = 60f

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas ?: return

        canvas.drawRect(0f, 0f, width.toFloat(), topAlign, maskPaint)
        canvas.drawRect(0f, topAlign, leftAlign, height - bottomAlign, maskPaint)
        canvas.drawRect(width - rightAlign, topAlign, width.toFloat(), height - bottomAlign, maskPaint)
        canvas.drawRect(0f, height - bottomAlign, width.toFloat(), height.toFloat(), maskPaint)

        canvas.drawRect(leftAlign, topAlign, width - rightAlign, height - bottomAlign, strokePaint)

        canvas.drawLine(width / 2f, 0f, width / 2f, height.toFloat(), linePaint)
        canvas.drawLine(0f, height / 2f, width.toFloat(), height / 2f, linePaint)
    }

}