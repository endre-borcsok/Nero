package com.ebsoftware.nero.core.data.stocks.di

import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.data.stocks.repository.LocalStockRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface StockRepositoryModule {

    @Binds
    @Singleton
    fun bindStockRepository(impl: LocalStockRepository): StockRepository
}