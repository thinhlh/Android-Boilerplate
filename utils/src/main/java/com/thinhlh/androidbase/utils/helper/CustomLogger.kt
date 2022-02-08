package com.thinhlh.androidbase.utils.helper

import com.orhanobut.logger.Logger
import com.thinhlh.androidbase.utils.constants.Constants

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 */
object CustomLogger {

    private const val showLog = Constants.DEBUG_MODE

    fun d(message: String) {
        if (showLog) {
            Logger.d(message)
        }
    }

    fun e(message: String) {
        if (showLog) {
            Logger.e(message)
        }
    }

    fun wtf(message: String) {
        if (showLog) {
            Logger.wtf(message)
        }
    }

    fun w(message: String) {
        if (showLog) {
            Logger.wtf(message)
        }
    }

    fun json(message: String) {
        if (showLog) {
            Logger.json(message)
        }
    }

    fun i(message: String) {
        if (showLog) {
            Logger.i(message)
        }
    }

    fun v(message: String) {
        if (showLog) {
            Logger.v(message)
        }
    }

    fun xml(message: String) {
        if (showLog) {
            Logger.xml(message)
        }
    }


}