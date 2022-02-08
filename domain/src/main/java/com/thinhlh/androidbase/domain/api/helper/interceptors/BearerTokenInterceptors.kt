package com.thinhlh.androidbase.domain.api.helper.interceptors

import okhttp3.Headers
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.SocketTimeoutException

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 * This class is used for intercept access token to the header of the request
 */
class BearerTokenInterceptors(
    private val accessToken: String? = null,
    private val onTimeout: () -> Unit
) : Interceptor {

    companion object {
        const val AUTHORIZATION = "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        var request: Request = chain.request()

        val requestBuilder = request.newBuilder()

        accessToken?.let { token ->
            requestBuilder.header(AUTHORIZATION, token)
        }

        request = requestBuilder.build()


        val response: Response?

        try {

            // Run request then check the response code
            response = chain.proceed(request)

            if (response.code == HttpURLConnection.HTTP_UNAUTHORIZED) {
                // TODO: token already expired or invalid -> Implement API refresh token | update new token

            }
        } catch (e: SocketTimeoutException) {
            e.printStackTrace()
            onTimeout()
        }

        return chain.proceed(request = request)
    }

}