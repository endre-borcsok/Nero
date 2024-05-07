package com.ebsoftware.nero.core.database.stocks.di

import android.content.Context
import androidx.room.Room
import com.ebsoftware.nero.core.database.stocks.StockDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object StockDatabaseModule {
    @Provides
    @Singleton
    fun providesStockPositionDatabase(
        @ApplicationContext context: Context,
    ): StockDatabase = Room.databaseBuilder(
        context,
        StockDatabase::class.java,
        "nero-stock-positions-database",
    ).build()
}