package com.example.card_info_finder.repository

import com.example.card_info_finder.model.ApiResponse
import com.example.card_info_finder.network.api.CardInfoApi
import javax.inject.Inject

class NetworkRepository {

    @Inject
    lateinit var cardInfoApi: CardInfoApi

    suspend fun getCardDetails(number : Int): ApiResponse{
        return cardInfoApi.getCardDetails(number)
    }
}