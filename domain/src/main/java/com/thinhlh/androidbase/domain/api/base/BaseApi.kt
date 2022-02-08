package com.thinhlh.androidbase.domain.api.base

import com.thinhlh.androidbase.domain.api.RetrofitService
import com.thinhlh.androidbase.domain.api.services.CommonService

/**
 * Created by thinhlh on 08/02/2022.
 * Copyright (c). All rights reserved
 *
 * This class is used to register API services
 */
abstract class BaseApi : BaseApiError() {
    // Services

    protected val commonService: CommonService =
        RetrofitService.get().createService(CommonService::class.java)
}