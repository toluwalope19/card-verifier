package com.example.card_info_finder.di.modules

import android.content.Context
import com.example.card_info_finder.network.api.CardInfoApi
import com.example.card_info_finder.network.interceptors.NetworkConnectivityInterceptor
import com.example.card_info_finder.network.interceptors.NetworkResponseInterceptor
import com.example.card_info_finder.repository.NetworkRepository
import com.example.card_info_finder.util.NetworkConstants
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {
    @Provides
    @Singleton
    fun provideOkhttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(NetworkConnectivityInterceptor(context))
            .addInterceptor(NetworkResponseInterceptor())
            .callTimeout(NetworkConstants.CALL_TIMEOUT, TimeUnit.MINUTES).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(NetworkConstants.BASE_URL)
            .client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideCardInfoService(retrofit: Retrofit): CardInfoApi {
        return retrofit.create(CardInfoApi::class.java)
    }

    @Provides
    fun provideRepository(cardInfoApi: CardInfoApi): NetworkRepository {
        return NetworkRepository(cardInfoApi)
    }


}