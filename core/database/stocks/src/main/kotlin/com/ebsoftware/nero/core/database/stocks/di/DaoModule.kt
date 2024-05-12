package com.ebsoftware.nero.core.database.stocks.di

import com.ebsoftware.nero.core.database.stocks.StockDatabase
import com.ebsoftware.nero.core.database.stocks.dao.StockPositionDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DaoModule {

    @Provides
    fun providesTopicsDao(
        database: StockDatabase,
    ): StockPositionDao = database.stockPositionDao()
}
