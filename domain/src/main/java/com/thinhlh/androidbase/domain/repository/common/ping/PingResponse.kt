package com.thinhlh.androidbase.domain.repository.common.ping

import com.google.gson.annotations.SerializedName
import com.thinhlh.androidbase.domain.repository.base.BaseResponse

/**
 * Created by thinhlh on 09/02/2022.
 * Copyright (c). All rights reserved
 */
class PingResponse(
    @SerializedName("data")
    override val data: String
) : BaseResponse<String>(data = data)