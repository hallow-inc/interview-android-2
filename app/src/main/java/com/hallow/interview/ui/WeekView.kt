package com.hallow.interview.ui

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import com.hallow.interview.extensions.toDp
import com.hallow.interview.models.Day

class WeekView(context: Context, attrs: AttributeSet? = null) : LinearLayout(context, attrs) {

    constructor(context: Context, days: List<Day?>) : this(context) {
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            weight = 1f
            setMargins(7.toDp(context), 0, 7.toDp(context), 0)
        }
        orientation = HORIZONTAL

        setDays(days)
    }

    private fun setDays(days: List<Day?>) {
        this.removeAllViews()

        var prevDay: Day? = null
        var currDay: Day? = null
        for ((i, day) in days.withIndex()) {
            if (i == 0) {
                currDay = day
                continue
            }

            if (currDay != null && currDay.hasSession) {
                if (prevDay?.hasSession == true && day?.hasSession == true) {
                    currDay.streak = Day.StreakPart.MIDDLE.text
                } else if (prevDay?.hasSession == true) {
                    currDay.streak = Day.StreakPart.END.text
                } else if (day?.hasSession == true) {
                    currDay.streak = Day.StreakPart.START.text
                }
            }
            addView(makeDay(context, currDay))

            prevDay = currDay
            currDay = day
        }
        if (prevDay?.hasSession == true && currDay?.hasSession == true) {
            currDay.streak = Day.StreakPart.END.text
        }
        addView(makeDay(context, currDay))
    }

    private fun makeDay(context: Context, day: Day? = null): View {
        val parent = LinearLayout(context)
        parent.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply {
            width = 0
            height = 40.toDp(context)
            weight = 1f
            gravity = Gravity.CENTER
            setMargins(0, 0, 0, 0)
        }
        parent.gravity = Gravity.CENTER
        parent.orientation = HORIZONTAL
        parent.addView(DayView(context, day))
        return parent
    }
}
