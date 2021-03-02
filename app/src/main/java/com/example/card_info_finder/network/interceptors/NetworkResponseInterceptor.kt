package com.example.card_info_finder.network.interceptors

import com.example.card_info_finder.network.exceptions.NetworkException
import com.example.card_info_finder.network.exceptions.ServerException
import okhttp3.Interceptor
import okhttp3.Response

class NetworkResponseInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request().newBuilder().build())
        when (response.code()) {
            in 400..499 -> throw NetworkException(response.code(), response.message())
            in 500..599 -> throw ServerException(response.code(), response.message())
        }
        return response
    }
}