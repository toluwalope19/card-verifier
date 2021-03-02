package com.example.card_info_finder.di

import androidx.lifecycle.ViewModel
import com.example.card_info_finder.viewmodels.CardViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CardViewModel::class)
    abstract fun bindViewModel(cardViewModel: CardViewModel): ViewModel
}