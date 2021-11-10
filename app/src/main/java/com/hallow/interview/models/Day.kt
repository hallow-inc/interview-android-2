package com.hallow.interview.models

import com.hallow.interview.extensions.isToday
import java.util.*

data class Day(
    val date: Date,
    val isToday: Boolean,
    val hasSession: Boolean,
    val goalMet: Boolean,
    var streak: String? = null
) {

    constructor(date: Date) : this(date, date.isToday(), false, false, null)

    val streakPart: StreakPart?
        get() = when (streak) {
            StreakPart.START.text -> StreakPart.START
            StreakPart.MIDDLE.text -> StreakPart.MIDDLE
            StreakPart.END.text -> StreakPart.END
            else -> null
        }

    enum class StreakPart(val text: String) {
        START("start"),
        MIDDLE("middle"),
        END("end")
    }
}
