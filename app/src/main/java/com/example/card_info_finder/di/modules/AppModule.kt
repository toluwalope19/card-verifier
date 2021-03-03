package com.example.card_info_finder.di.modules

import android.content.Context
import com.example.card_info_finder.BaseApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule(private val app: BaseApplication) {


    @Provides
    @Singleton
    fun provideContext(): Context = app.applicationContext

}