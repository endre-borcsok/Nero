package com.ebsoftware.nero.core.stocks.retrofit

import com.ebsoftware.nero.core.stocks.StocksApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class RetrofitStocksApi @Inject constructor(
    private val service: RetrofitStocksService,
) : StocksApi