package com.hallow.interview.api.repositories

import com.hallow.interview.api.MainApi
import com.hallow.interview.extensions.addMonths
import com.hallow.interview.extensions.firstDayOfMonth
import com.hallow.interview.extensions.lastDayOfMonth
import com.hallow.interview.models.Day
import com.hallow.interview.models.Month
import java.util.*
import kotlin.collections.ArrayList
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.then

class ProfileRepository(
    private val api: MainApi
) {

    fun getActivity(): Promise<List<Day>, Exception> = api.getActivity().process()
}
