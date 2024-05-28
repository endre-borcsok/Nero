package com.ebsoftware.nero.testing.android.di

import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.data.stocks.di.StockRepositoryModule
import com.ebsoftware.nero.testing.android.repository.TestStocksRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [StockRepositoryModule::class],
)
internal abstract class TestStocksRepositoryModule {

    @Binds
    @Singleton
    abstract fun bindStocksRepository(
        impl: TestStocksRepository,
    ): StockRepository
}
