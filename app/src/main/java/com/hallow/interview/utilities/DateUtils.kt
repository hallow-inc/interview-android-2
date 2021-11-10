package com.hallow.interview.utilities

import com.hallow.interview.extensions.formatDayOfWeekLetter
import com.hallow.interview.extensions.setDayOfWeek
import java.util.*

class DateUtils {

    companion object {

        /**
         * dayOfWeek 1 = Sunday, 7 = Saturday
         */
        fun getDayOfWeekLetter(dayOfWeek: Int): String =
            Date().setDayOfWeek(dayOfWeek).formatDayOfWeekLetter()
    }
}
