package com.thinhlh.androidbase.domain.api.services

import com.thinhlh.androidbase.domain.repository.common.ping.PingResponse
import retrofit2.Call
import retrofit2.http.GET

/**
 * Created by thinhlh on 09/02/2022.
 * Copyright (c). All rights reserved
 */
interface CommonService {
    @GET("ping")
    fun ping(): Call<PingResponse>
}