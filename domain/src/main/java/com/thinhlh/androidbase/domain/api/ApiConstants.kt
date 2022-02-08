package com.thinhlh.androidbase.domain.api

import com.thinhlh.androidbase.utils.constants.Constants

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 */
object ApiConstants {
    private const val DEV_URL = ""

    private const val PROD_URL = ""

    val BASE_URL = if (Constants.DEBUG_MODE) DEV_URL else PROD_URL
}