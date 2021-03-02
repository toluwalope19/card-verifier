package com.example.card_info_finder

import com.example.card_info_finder.di.AppModule
import com.example.card_info_finder.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

    override fun applicationInjector(): AndroidInjector<out DaggerApplication>? {
        return DaggerAppComponent.builder()
            .application(this)
            .appModule(AppModule(this))
            .build()
    }
}