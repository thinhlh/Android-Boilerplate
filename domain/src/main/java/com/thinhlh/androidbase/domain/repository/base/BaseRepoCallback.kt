package com.thinhlh.androidbase.domain.repository.base

/**
 * Created by thinhlh on 09/02/2022.
 * Copyright (c). All rights reserved
 * @param T response class
 */
interface BaseRepoCallback<T> {

    /**
     * Called when api is requesting
     * @param showLoading indicate whether should show loading when calling the api
     * */
    fun apiRequesting(showLoading: Boolean) = Unit

    // Called when api response return failed code (e.g 404, 401) or success = false
    fun showMessage(message: String?) = Unit

    // Called when api response success
    fun apiResponse(data: T?)

    // Called when calling api from device failed
    fun apiFailure() = Unit
}