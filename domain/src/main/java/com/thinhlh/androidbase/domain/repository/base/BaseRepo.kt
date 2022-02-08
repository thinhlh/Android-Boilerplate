package com.thinhlh.androidbase.domain.repository.base

import com.thinhlh.androidbase.domain.api.base.BaseApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by thinhlh on 09/02/2022.
 * Copyright (c). All rights reserved
 *
 * This class is where you define response handlers including:
 * 1. Should the api call show loading indicator
 * 2. Which response model the api should return (getBodyResponse)
 * 3. Which exception or message should be displayed when there is api exception
 * 4. In production, @param T can be replaced by Base Respons be cause the base response is universial for all apis
 */
abstract class BaseRepo : BaseApi() {
    protected fun <T> getApiCallback(callback: BaseRepoCallback<T>): Callback<T> {

        // Display loading indicator
        callback.apiRequesting(true)

        return object : Callback<T> {
            override fun onResponse(call: Call<T>, response: Response<T>) {

                // Automatically dismiss the loading indicator
                callback.run {
                    apiRequesting(false)

                    if (response.isSuccessful) {
                        apiResponse(getBodyResponse(response))
                        return
                    }
                    showMessage(getErrorMessage(response))
                }
            }

            override fun onFailure(call: Call<T>, t: Throwable) {
                callback.run {
                    apiRequesting(false)
                    apiFailure()
                }
            }

        }
    }


    // Define how to parse JSON body to model

    protected fun <T> getBodyResponse(response: Response<T>): T? {
        if (response.isSuccessful) {
            val bodyResponse = response.body()
            bodyResponse?.let { body -> return body }
        }
        return null
    }
}