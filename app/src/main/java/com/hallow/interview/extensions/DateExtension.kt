package com.hallow.interview.extensions

import java.util.*

fun Date.addMonths(months: Number): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.add(Calendar.MONTH, months.toInt())
    return cal.time
}

fun Date.lastDayOfMonth(): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE))
    return cal.time
}

fun Date.firstDayOfMonth(): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.set(Calendar.DATE, cal.getActualMinimum(Calendar.DATE))
    return cal.time
}

fun Date.weekday(): Int {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal.get(Calendar.DAY_OF_WEEK)
}

fun Date.getDayOfMonth(): Int {
    val cal = Calendar.getInstance()
    cal.time = this
    return cal.get(Calendar.DAY_OF_MONTH)
}

fun Date.setDayOfWeek(dayOfWeek: Int): Date {
    val cal = Calendar.getInstance()
    cal.time = this
    cal.set(Calendar.DAY_OF_WEEK, dayOfWeek)
    return cal.time
}

fun Date.isSameDay(date: Date): Boolean {
    val cal1 = toCalendar()
    val cal2 = date.toCalendar()
    return cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR) &&
        cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
}

fun Date.isToday() = this.isSameDay(Date())

fun Date.toCalendar(): Calendar = Calendar.getInstance().apply {
    time = this@toCalendar
}
