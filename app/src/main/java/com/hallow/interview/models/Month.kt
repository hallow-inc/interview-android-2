package com.hallow.interview.models

import android.os.Build
import android.util.SparseArray
import com.hallow.interview.extensions.firstDayOfMonth
import com.hallow.interview.extensions.getDayOfMonth
import com.hallow.interview.extensions.isSameDay
import com.hallow.interview.extensions.lastDayOfMonth
import com.hallow.interview.extensions.toCalendar
import com.hallow.interview.extensions.weekday
import java.time.Month
import java.time.format.TextStyle
import java.util.*

class Month(
    val month: Calendar,
    val days: List<Day>
) {

    constructor(date: Date, days: List<Day>) : this(
        month = date.toCalendar().apply {
            set(Calendar.DATE, 1)
            set(Calendar.HOUR, 0)
            set(Calendar.MINUTE, 0)
            set(Calendar.SECOND, 0)
        },
        days = days
    )

    val year: Int
        get() = month.get(Calendar.YEAR)

    val name: String
        get() = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Month.of(month.get(Calendar.MONTH) + 1)
                .getDisplayName(TextStyle.FULL, Locale.getDefault())
        } else {
            String.format(Locale.US, "%tB", month)
        }

    val firstWeekDay: Int
        get() = month.time.firstDayOfMonth().weekday()

    val daysInMonth: Int
        get() = month.time.lastDayOfMonth().getDayOfMonth()

    override fun toString() = month.time.toString()
}
