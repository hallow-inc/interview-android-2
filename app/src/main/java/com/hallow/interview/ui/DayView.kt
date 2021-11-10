package com.hallow.interview.ui

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.text.format.DateUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.View
import android.widget.LinearLayout
import androidx.annotation.ColorInt
import androidx.core.content.ContextCompat
import com.hallow.interview.R
import com.hallow.interview.extensions.colorFromAttr
import com.hallow.interview.extensions.getAttributeColor
import com.hallow.interview.extensions.toDp
import com.hallow.interview.models.Day
import java.util.*

class DayView(context: Context, attrs: AttributeSet? = null, defStyle: Int = -1) : View(context, attrs, defStyle) {

    private val smallSize = 10.toDp(resources)
    private val largeSize = 30.toDp(resources)

    private var cornerRadius: Float = 0.toFloat()

    private var hasPrayer: Boolean = false
    private var inMonth: Boolean = false
    private var date: Date? = null
    private var size: Int = smallSize
    private var streak: Day.StreakPart? = null
    private var metGoal: Boolean = false
    private var isToday: Boolean = false

    constructor(context: Context, day: Day?) : this(context) {
        day?.let {
            this.hasPrayer = day.hasSession
            this.inMonth = true
            this.date = day.date
            this.size = if (hasPrayer) largeSize else smallSize
            this.streak = day.streakPart
            this.metGoal = day.goalMet
            this.isToday = DateUtils.isToday(day.date.time)
        }

        init(context)

        val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        params.height = size.toDp(resources)
        params.width = size.toDp(resources)
        this.layoutParams = params
    }

    private fun init(context: Context) {
        val metrics = context.resources.displayMetrics
        cornerRadius = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, size.toFloat(), metrics)

        setWillNotDraw(false)
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)

        val paint = Paint(Paint.ANTI_ALIAS_FLAG)
        val fillColor = circleColor()
        fillCircleStrokeBorder(canvas, width.toFloat() / 2, height.toFloat() / 2, this.size.toFloat() / 2, fillColor, paint)
        when (streak) {
            Day.StreakPart.START -> fillRect(canvas, width.toFloat() / 2, width.toFloat(), fillColor, paint)
            Day.StreakPart.MIDDLE -> fillRect(canvas, 0f, width.toFloat(), fillColor, paint)
            Day.StreakPart.END -> fillRect(canvas, 0f, width.toFloat() / 2, fillColor, paint)
        }
        if (isToday) {
            fillCircleStrokeBorder(
                canvas,
                width.toFloat() / 2,
                height.toFloat() / 2,
                smallSize.toFloat() / 2,
                context.colorFromAttr(R.attr.colorOnBackground),
                paint
            )
        }
    }

    private fun fillCircleStrokeBorder(
        c: Canvas,
        cx: Float,
        cy: Float,
        radius: Float,
        circleColor: Int,
        p: Paint
    ) {

        val saveColor = p.color
        p.color = circleColor
        val saveStyle = p.style
        p.style = Paint.Style.FILL
        c.drawCircle(cx, cy, radius, p)
        p.color = saveColor
        p.style = saveStyle
    }

    private fun fillRect(c: Canvas, start: Float, end: Float, circleColor: Int, p: Paint) {

        val saveColor = p.color
        p.color = circleColor
        val saveStyle = p.style
        p.style = Paint.Style.FILL

        val middle = height / 2
        val offset = size / 2
        val top = middle - offset
        val bottom = middle + offset

        c.drawRect(start, top.toFloat(), end, bottom.toFloat(), p)
        p.color = saveColor
        p.style = saveStyle
    }

    @ColorInt
    private fun circleColor(): Int {
        date?.let {
            return when {
                hasPrayer -> ContextCompat.getColor(context, R.color.aqua)
                metGoal -> ContextCompat.getColor(context, R.color.aqua40)
                else -> context.getAttributeColor(R.attr.colorGrey2)
            }
        }

        return Color.TRANSPARENT
    }
}
