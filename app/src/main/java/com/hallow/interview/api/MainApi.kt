package com.hallow.interview.api

import com.hallow.interview.api.adapters.NetworkPromise
import com.hallow.interview.models.Day
import retrofit2.http.GET

interface MainApi {

    @GET("activity.json")
    fun getActivity(): NetworkPromise<List<Day>>
}
