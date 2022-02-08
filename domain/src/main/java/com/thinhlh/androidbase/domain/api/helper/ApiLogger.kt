package com.thinhlh.androidbase.domain.api.helper

import android.util.Log
import com.thinhlh.androidbase.utils.helper.CustomLogger
import okhttp3.logging.HttpLoggingInterceptor
import java.lang.Exception

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 */
class ApiLogger : HttpLoggingInterceptor.Logger {

    // Define your custom log here depends on the api when it have error
    override fun log(message: String) {
        if (message.startsWith("{") || message.startsWith("[")) {
            try {
                CustomLogger.json(message)
            } catch (e: Exception) {
                Log.e("API exception: ", e.toString())
            }
        } else {
            Log.d("API: ", message)
            return
        }
    }
}