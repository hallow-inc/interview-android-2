package com.hallow.interview.di.module

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.hallow.interview.BuildConfig
import com.hallow.interview.api.MainApi
import com.hallow.interview.api.adapters.DateAdapter
import com.hallow.interview.api.adapters.PromiseCallAdapterFactory
import com.hallow.interview.utilities.Constants
import dagger.Module
import dagger.Provides
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMainApi(retrofit: Retrofit): MainApi = retrofit.create(MainApi::class.java)

    @Provides
    @Singleton
    fun provideRetrofit(gson: Gson, okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(PromiseCallAdapterFactory.create())
            .client(okHttpClient)
            .baseUrl(Constants.apiUrl)
            .build()
    }

    @Provides
    fun providesGson(): Gson {
        return GsonBuilder()
            .serializeNulls()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .registerTypeAdapter(Date::class.java, DateAdapter())
            .create()
    }

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return interceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClientDefault(
        loggingInterceptor: HttpLoggingInterceptor
    ): OkHttpClient {
        val okBuilder = OkHttpClient.Builder()
        okBuilder.addInterceptor(loggingInterceptor)
        okBuilder.addInterceptor(headersInterceptor())

        // Add timeouts
        okBuilder.connectTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okBuilder.readTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)
        okBuilder.writeTimeout(TIMEOUT_SECONDS, TimeUnit.SECONDS)

        return okBuilder.build()
    }

    private fun headersInterceptor() = Interceptor { chain ->
        val builder = chain.request().newBuilder()
        headers.forEach {
            builder.addHeader(it.key, it.value)
        }
        chain.proceed(builder.build())
    }

    companion object {
        const val TIMEOUT_SECONDS = 30L

        val headers: Map<String, String>
            get() = hashMapOf(
                "Accept" to "application/json",
                "Content-Type" to "application/json"
            )
    }
}
