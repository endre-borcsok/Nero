package com.ebsoftware.nero.core.stocks.di

import com.ebsoftware.nero.core.stocks.StocksApi
import com.ebsoftware.nero.core.stocks.retrofit.RetrofitStocksApi
import com.ebsoftware.nero.core.stocks.retrofit.RetrofitStocksServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface StocksNetworkModule {

    @Binds
    @Singleton
    fun bindStocksApi(impl: RetrofitStocksApi): StocksApi

    companion object {

        @Provides
        @Singleton
        fun provideRetrofitStocksService(
            factory: RetrofitStocksServiceFactory
        ) = factory()
    }
}