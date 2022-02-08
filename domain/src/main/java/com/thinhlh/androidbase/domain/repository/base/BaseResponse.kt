package com.thinhlh.androidbase.domain.repository.base

import com.google.gson.annotations.SerializedName

/**
 * Created by thinhlh on 09/02/2022.
 * Copyright (c). All rights reserved
 *
 * @param T is the type of data response
 */
abstract class BaseResponse<T>(

    @SerializedName("success")
    val success: Boolean = false,

    @SerializedName("message")
    val message: String? = "",

    @SerializedName("data")
    open val data: T
)