package com.example.card_info_finder.viewmodels

import android.util.Log
import android.util.Log.INFO
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.card_info_finder.model.ApiResponse
import com.example.card_info_finder.network.exceptions.NetworkException
import com.example.card_info_finder.network.exceptions.NoNetworkException
import com.example.card_info_finder.network.exceptions.ServerException
import com.example.card_info_finder.repository.NetworkRepository
import com.example.card_info_finder.util.Resource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.logging.Level.INFO
import javax.inject.Inject

class CardViewModel @Inject constructor() :
    ViewModel() {

    @Inject
    lateinit var networkRepository: NetworkRepository

    var response = MutableLiveData<Resource<ApiResponse>>()

    fun getDetails(number: Int) {

        CoroutineScope(Dispatchers.Main).launch {
            response.postValue(Resource.loading(data = null))
            try {
                val requestResponse = networkRepository.getCardDetails(number)
                response.postValue(Resource.success(requestResponse))
                Log.i("response", response.toString())
            } catch (t: Throwable) {
                Log.e("error", t.message.toString())
                response.postValue(
                    Resource.error(
                        message = when (t) {
                            is NetworkException -> "Error: ${t.code}, Incorrect card details"
                            is NoNetworkException -> "Please check your connectivity and try again"
                            is ServerException -> "Cannot connect to server"
                            else -> "Oops, something went wrong, Please try Again"
                        }, data = null
                    )
                )
            }
        }

    }


}