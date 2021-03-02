package com.example.card_info_finder.viewmodels

import androidx.lifecycle.ViewModel
import com.example.card_info_finder.repository.NetworkRepository
import javax.inject.Inject

class CardViewModel @Inject constructor(private val networkRepository: NetworkRepository)
    : ViewModel() {


}