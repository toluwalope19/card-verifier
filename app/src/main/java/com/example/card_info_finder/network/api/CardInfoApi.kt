package com.example.card_info_finder.network.api

import com.example.card_info_finder.util.NetworkConstants
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CardInfoApi {

    @Headers("Accept: Accept-Version: 3")
    @GET("https://lookup.binlist.net/{number}")
    suspend fun getCardDetails(@Path("number") number: Int)


}