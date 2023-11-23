package com.example.views.customview

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.Rect
import android.graphics.RectF
import android.util.AttributeSet
import android.view.View
import com.example.views.R
import java.util.Calendar
import kotlin.math.cos
import kotlin.math.min
import kotlin.math.sin

class ClockView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
    defStyleRes: Int = 0
) : View(context, attrs, defStyle, defStyleRes) {

    private var height: Int = 0
    private var width: Int = 0
    private var radius: Int = 0
    private var padding: Int = 0
    private var centerX: Int = 0
    private var centerY: Int = 0
    private var min: Int = 0
    private var angle: Double = 0.0
    private lateinit var paint: Paint
    private lateinit var path: Path
    private lateinit var rect: Rect
    private var hourHandSize: Int = 0
    private var handSize: Int = 0
    private lateinit var numbers: IntArray
    private var hour: Float = 0F
    private var minute: Float = 0F
    private var second: Float = 0F
    private var fontSize: Int = 0
    private var eyeWidth = 0
    private var eyeHeight = 0
    private var eyeRadius: Float = 0.0f
    private var eyeCenterX: Int = 0
    private var eyeCenterY: Int = 0
    private var eyeCenterX1: Int = 0
    private var eyeCenterY1: Int = 0
    private var pupilRadius: Float = 0.0f
    private var eyeColor: Int = 0


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        drawTriangle(canvas, 40f, 680F)
        canvas.restore()
        drawTriangle(canvas, -40f, 120F)
        drawCircle(canvas)
        drawEye(canvas, eyeCenterX.toFloat(), eyeCenterY.toFloat(), eyeRadius)
        drawEye(canvas, eyeCenterX1.toFloat(), eyeCenterY1.toFloat(), eyeRadius)
        drawHands(canvas)
        drawNumerals(canvas)
        postInvalidateDelayed(500)
    }

    init {
        initClock(context)
    }

    private fun initClock(
        context: Context
    ) {
        height = 800
        width = 800
        padding = 30
        centerX = width / 2
        centerY = height / 2
        min = height.coerceAtMost(width)
        radius = min / 2 - padding
        angle = Math.PI / 30 - Math.PI / 2
        paint = Paint()
        path = Path()
        rect = Rect()
        hourHandSize = radius - radius / 2
        handSize = radius - radius / 4
        numbers = intArrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)
        fontSize = 20
        eyeWidth = 300
        eyeHeight = 300
        eyeCenterX = eyeWidth / 2 + 410
        eyeCenterY = eyeHeight / 2 + 180
        eyeRadius = min(eyeCenterX, eyeCenterY) * 0.2f
        pupilRadius = eyeRadius * 0.6f
        eyeCenterX1 = eyeWidth / 2 + 90
        eyeCenterY1 = eyeHeight / 2 + 180
        eyeColor = context.getColor(R.color.yellow)
    }

    private fun drawCircle(canvas: Canvas) {
        paint.reset()
        canvas.restore()
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE, 15)
        canvas.drawCircle(centerX.toFloat(), centerY.toFloat(), radius.toFloat(), paint)
    }

    private fun drawTriangle(canvas: Canvas, degrees: Float, center: Float) {
        paint.reset()
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE, 0)
        val topY = 0f
        val bottomY = 400.toFloat() - 100f
        val path = Path()
        path.moveTo(center, topY)
        path.lineTo(center - 200f, bottomY)
        path.lineTo(center + 200f, bottomY)
        path.close()
        canvas.save()
        canvas.rotate(degrees, center, (topY + bottomY) / 2f)
        canvas.drawPath(path, paint)
    }

    private fun drawEye(
        canvas: Canvas,
        centerX: Float,
        centerY: Float,
        eyeRadius: Float
    ) {
        paint.reset()
        setPaintAttributes(eyeColor, Paint.Style.STROKE, 0)
        canvas.drawCircle(centerX, centerY, eyeRadius, paint)
        paint.reset()
        val ovalWidth = 100 * 0.3f
        val ovalHeight = 100
        val left = centerX - ovalWidth / 2f
        val top = centerY - ovalHeight / 2f
        val right = centerX + ovalWidth / 2f
        val bottom = centerY + ovalHeight / 2f
        setPaintAttributes(Color.BLACK, Paint.Style.STROKE, 0)
        canvas.drawOval(RectF(left, top, right, bottom), paint)
    }

    private fun setPaintAttributes(color: Int, stroke: Paint.Style, strokeWidth: Int) {
        paint.reset()
        paint.color = color
        paint.style = stroke
        paint.style = Paint.Style.FILL
        paint.strokeWidth = strokeWidth.toFloat()
        paint.isAntiAlias = true
    }

    private fun drawHands(canvas: Canvas) {
        val calendar: Calendar = Calendar.getInstance()
        hour = calendar.get(Calendar.HOUR_OF_DAY).toFloat()
        hour = if (hour > 12) {
            hour - 12
        } else {
            hour
        }
        minute = calendar.get(Calendar.MINUTE).toFloat()
        second = calendar.get(Calendar.SECOND).toFloat()

        drawHourHand(canvas, (hour + minute / 60.0) * 5F)
        drawMinuteHand(canvas, minute)
        drawSecondHand(canvas, second)


    }

    private fun drawMinuteHand(canvas: Canvas, location: Float) {
        paint.reset()
        setPaintAttributes(Color.WHITE, Paint.Style.STROKE, 8)
        angle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(
            centerX.toFloat(),
            centerY.toFloat(),
            (centerX + cos(angle) * handSize).toFloat(),
            (centerY + sin(angle) * hourHandSize).toFloat(),
            paint
        )
    }

    private fun drawHourHand(canvas: Canvas, location: Double) {
        paint.reset()
        setPaintAttributes(Color.GRAY, Paint.Style.STROKE, 10)
        angle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(
            centerX.toFloat(),
            centerY.toFloat(),
            (centerX + cos(angle) * hourHandSize).toFloat(),
            (centerY + sin(angle) * hourHandSize).toFloat(),
            paint
        )
    }

    private fun drawSecondHand(canvas: Canvas, location: Float) {
        paint.reset()
        setPaintAttributes(Color.RED, Paint.Style.STROKE, 8)
        angle = Math.PI * location / 30 - Math.PI / 2
        canvas.drawLine(
            centerX.toFloat(),
            centerY.toFloat(),
            (centerX + cos(angle) * handSize).toFloat(),
            (centerY + sin(angle) * hourHandSize).toFloat(),
            paint
        )
    }

    private fun drawNumerals(canvas: Canvas) {
        paint.textSize = 45F
        paint.color = context.getColor(R.color.lav)

        for (number: Int in numbers) {
            val num = number.toString()
            paint.getTextBounds(num, 0, num.length, rect)
            angle = Math.PI / 6 * (number - 3)
            val x = centerX + cos(angle) * (radius - 35) - rect.width() / 2
            val y = centerY + sin(angle) * (radius - 35) + rect.width() / 2
            canvas.drawText(num, x.toFloat(), y.toFloat(), paint)
        }
    }


}