package com.ebsoftware.nero.core.converter.stocks.hl.di

import com.ebsoftware.nero.core.converter.stocks.hl.HLConverter
import com.ebsoftware.nero.core.converter.stocks.hl.csv.HLCSVConverter
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal interface HLConverterModule {
    @Binds
    @Singleton
    fun bindHLConverter(impl: HLCSVConverter): HLConverter
}
