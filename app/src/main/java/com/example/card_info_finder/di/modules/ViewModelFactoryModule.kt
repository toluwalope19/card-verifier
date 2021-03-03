package com.example.card_info_finder.di.modules


import androidx.lifecycle.ViewModelProvider
import com.example.card_info_finder.di.ViewModelProviderFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelProviderFactory: ViewModelProviderFactory) : ViewModelProvider.Factory
}