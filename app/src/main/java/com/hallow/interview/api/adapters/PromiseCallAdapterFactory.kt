package com.hallow.interview.api.adapters

import java.lang.reflect.ParameterizedType
import java.lang.reflect.Type
import retrofit2.Call
import retrofit2.CallAdapter
import retrofit2.Retrofit

class PromiseCallAdapterFactory private constructor() : CallAdapter.Factory() {

    class PromiseCallAdapter<R>(private val responseType: Type) : CallAdapter<R, Any> {
        override fun responseType(): Type = responseType
        override fun adapt(call: Call<R>): Any = NetworkPromise(call)
    }

    override fun get(returnType: Type?, annotations: Array<out Annotation>?, retrofit: Retrofit?): CallAdapter<*, *>? {
        return returnType?.let {
            return try {
                val enclosingType = it as? ParameterizedType
                if (enclosingType?.rawType != NetworkPromise::class.java) {
                    null
                } else {
                    val type = enclosingType.actualTypeArguments[0]
                    PromiseCallAdapter<Any>(type)
                }
            } catch (ex: ClassCastException) {
                null
            }
        }
    }

    companion object {
        @JvmStatic
        fun create() = PromiseCallAdapterFactory()
    }
}
