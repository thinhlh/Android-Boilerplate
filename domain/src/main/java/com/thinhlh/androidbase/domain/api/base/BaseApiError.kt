package com.thinhlh.androidbase.domain.api.base

import com.google.gson.annotations.SerializedName
import com.thinhlh.androidbase.domain.repository.base.BaseResponse
import retrofit2.Response

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 * This class is used as a model when API response return exceptions
 */
abstract class BaseApiError(
    @SerializedName("data")
    override val data: String? = ""

) : BaseResponse<String?>(data = data) {
    fun <T> getErrorMessage(apiResponse: Response<T>): String {
        return data ?: ""
    }
}
