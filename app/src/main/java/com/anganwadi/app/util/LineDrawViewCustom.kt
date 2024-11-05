package com.anganwadi.app.util

import android.animation.ValueAnimator
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import kotlin.math.abs
import kotlin.math.pow
import kotlin.random.Random

class LineDrawViewCustom(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val paint = Paint().apply {
        strokeWidth = 5f
        style = Paint.Style.STROKE
        isAntiAlias = true
    }
    private val path = Path()
    private val lines = mutableListOf<CurvedLine>()
    private val animatingLines = mutableListOf<AnimatingLine>()
    private val random = Random(System.currentTimeMillis())

    data class CurvedLine(
        val startX: Float, val startY: Float,
        val controlX: Float, val controlY: Float,
        val endX: Float, val endY: Float,
        val color: Int
    )

    inner class AnimatingLine(val line: CurvedLine) {
        var progress: Float = 0f
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        lines.forEach { line ->
            paint.color = line.color
            drawCurvedLine(canvas, line, 1f)
        }
        animatingLines.forEach { animatingLine ->
            paint.color = animatingLine.line.color
            drawCurvedLine(canvas, animatingLine.line, animatingLine.progress)
        }
    }

    private fun drawCurvedLine(canvas: Canvas, line: CurvedLine, progress: Float) {
        path.reset()
        path.moveTo(line.startX, line.startY)
        path.quadTo(
            line.controlX, line.controlY,
            line.startX + (line.endX - line.startX) * progress,
            line.startY + (line.endY - line.startY) * progress
        )
        canvas.drawPath(path, paint)
    }

    fun addLine(startX: Float, startY: Float, endX: Float, endY: Float, color: Int) {
        val controlX = (startX + endX) / 2 + randomOffset((endX - startX) * 0.2f)
        val controlY = (startY + endY) / 2 + calculateCurveOffset(startX, startY, endX, endY)

        val newLine = CurvedLine(startX, startY, controlX, controlY, endX, endY, color)
        val animatingLine = AnimatingLine(newLine)
        animatingLines.add(animatingLine)

        ValueAnimator.ofFloat(0f, 1f).apply {
            duration = 200 // Random duration between 500 and 1500 milliseconds
            interpolator = LinearInterpolator()
            addUpdateListener { animator ->
                animatingLine.progress = animator.animatedValue as Float
                invalidate()
            }
            start()
        }.also { animator ->
            animator.addListener(object : android.animation.AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: android.animation.Animator) {
                    animatingLines.remove(animatingLine)
                    lines.add(newLine)
                    invalidate()
                }
            })
        }
    }

    private fun calculateCurveOffset(startX: Float, startY: Float, endX: Float, endY: Float): Float {
        val distance = kotlin.math.sqrt((endX - startX).pow(2) + (endY - startY).pow(2))
        val baseOffset = distance * 0.2f * (if (abs(endY - startY) > abs(endX - startX)) -1 else 1)
        val randomFactor = Math.sin(random.nextDouble() * 2 * Math.PI) * 0.5f // Adjust amplitude and frequency as needed
        return (baseOffset + randomOffset(distance * 0.1f) + randomFactor).toFloat()
    }

    private fun randomOffset(maxOffset: Float): Float {
        return random.nextFloat() * maxOffset * 2 - maxOffset
    }

    fun clearLines() {
        lines.clear()
        animatingLines.clear()
        invalidate()
    }
}