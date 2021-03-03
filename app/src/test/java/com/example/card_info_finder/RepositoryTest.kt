package com.example.card_info_finder

import com.example.card_info_finder.model.ApiResponse
import com.example.card_info_finder.model.Bank
import com.example.card_info_finder.model.Country
import com.example.card_info_finder.network.api.CardInfoApi
import com.example.card_info_finder.repository.NetworkRepository
import com.nhaarman.mockitokotlin2.given
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
@ExperimentalCoroutinesApi
class RepositoryTest {


    private lateinit var underTest: NetworkRepository
    val api: CardInfoApi = mock()

    @Before
    fun setUp() {
        underTest = NetworkRepository(api)
    }

    @Test
    fun getDetails(){
        runBlocking {
            val dummyCardNumber = 45717360
           //given
            given(api.getCardDetails(dummyCardNumber)).willReturn(dummyResponse)

            //then
            verify(api).getCardDetails(dummyCardNumber)
        }
    }


    private val dummyResponse = ApiResponse(
        Bank("Hjørring","Jyske Bank","+4589893300","www.jyskebank.dk")
        ,"Visa/Dankort",
        Country("DK","DKK","🇩🇰",10,56,"Denmark","208"),
        com.example.card_info_finder.model.Number(16,true),false,"visa","debit"
    )

}
