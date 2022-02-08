package com.thinhlh.androidbase.domain.api

import com.thinhlh.androidbase.domain.api.helper.ApiLogger
import com.thinhlh.androidbase.domain.api.helper.interceptors.BearerTokenInterceptors
import com.thinhlh.androidbase.utils.constants.Constants
import okhttp3.OkHttpClient
import okhttp3.Protocol
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 */
class RetrofitService private constructor() {
    private lateinit var timeOut: () -> Unit

    private val loggingInterceptor: HttpLoggingInterceptor =
        HttpLoggingInterceptor(ApiLogger()).setLevel(
            if (Constants.DEBUG_MODE)
                HttpLoggingInterceptor.Level.BODY
            else HttpLoggingInterceptor.Level.NONE
        )

    private var bearerTokenInterceptor: BearerTokenInterceptors? = null

    fun init(accessToken: String? = null, timeOut: (() -> Unit)? = null) {
        timeOut?.let {
            this.timeOut = it
        }

        bearerTokenInterceptor = BearerTokenInterceptors(accessToken, onTimeout = this.timeOut)
    }

    private fun createBuilder(): Retrofit {
        val okHttpBuilder = OkHttpClient.Builder()
            .readTimeout(REQ_TIME_OUT, TimeUnit.SECONDS)
            .writeTimeout(REQ_TIME_OUT, TimeUnit.SECONDS)
            .connectTimeout(REQ_TIME_OUT, TimeUnit.SECONDS)
            .protocols(listOf(Protocol.HTTP_1_1))
            .apply {
                addInterceptor(loggingInterceptor)
                bearerTokenInterceptor?.let {
                    addInterceptor(it)
                }
            }
            .build()

        return Retrofit.Builder()
            .baseUrl(ApiConstants.BASE_URL)
            .client(okHttpBuilder)
            .addConverterFactory(GsonConverterFactory.create())
            .addConverterFactory(ScalarsConverterFactory.create())
            .build()
    }

    /**
     * Create API service
     */
    fun <T> createService(serviceClazz: Class<T>): T {
        return createBuilder().create(serviceClazz)
    }

    companion object {

        @Volatile
        private var instance: RetrofitService? = null

        fun get(): RetrofitService =
            instance ?: synchronized(this) {
                val newInstance =
                    instance ?: RetrofitService().also { instance = it }
                newInstance
            }

        private const val REQ_TIME_OUT: Long = 60
    }

}