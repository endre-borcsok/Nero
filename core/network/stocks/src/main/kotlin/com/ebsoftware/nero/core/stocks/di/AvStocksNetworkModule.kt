package com.ebsoftware.nero.core.stocks.di

import com.ebsoftware.nero.core.stocks.StocksApi
import com.ebsoftware.nero.core.stocks.alphavantage.AvBaseUrl
import com.ebsoftware.nero.core.stocks.alphavantage.AvRetrofitStocksApi
import com.ebsoftware.nero.core.stocks.alphavantage.AvRetrofitStocksServiceFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object AvStocksNetworkModule {

    @Provides
    @Singleton
    fun provideRetrofitStocksService(
        factory: AvRetrofitStocksServiceFactory
    ) = factory()

    @Provides
    @Singleton
    fun provideRetrofitBaseUrl() = AvBaseUrl.URL

    @Provides
    @Singleton
    fun provideApiKey() = "demo"
}