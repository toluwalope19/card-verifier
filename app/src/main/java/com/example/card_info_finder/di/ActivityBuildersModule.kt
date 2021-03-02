package com.example.card_info_finder.di

import com.example.card_info_finder.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector()
    abstract fun contributeMainActivity(): MainActivity
}