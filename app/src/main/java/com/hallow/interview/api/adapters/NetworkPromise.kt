package com.hallow.interview.api.adapters

import com.google.gson.JsonParser
import nl.komponents.kovenant.Promise
import nl.komponents.kovenant.deferred
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkPromise<R>(private val call: Call<R>) {

    class ApiException(
        message: String
    ) : Exception(message)

    fun process(): Promise<R, Exception> {

        val deferred = deferred<R, Exception>()

        val callback = object : Callback<R> {

            override fun onFailure(call: Call<R>, t: Throwable) {
                when (t) {
                    is Exception -> deferred.reject(t)
                    else -> deferred.reject(Exception(t.localizedMessage))
                }
            }

            override fun onResponse(call: Call<R>, response: Response<R>) {
                if (!response.isSuccessful && response.code() in ERROR_CODE_RANGE) {
                    val errorJsonString = response.errorBody()?.string()
                    val message = try {
                        JsonParser().parse(errorJsonString).asJsonObject["error"].asString
                    } catch (e: Exception) {
                        "An error occurred please try again."
                    }

                    deferred.reject(ApiException(message))
                } else {
                    response.body()?.let {
                        deferred.resolve(it)
                    } ?: deferred.reject(Exception("Unable to parse response body."))
                }
            }
        }

        call.enqueue(callback)

        return deferred.promise
    }

    companion object {
        val ERROR_CODE_RANGE = 400..511
    }
}
