package com.ebsoftware.nero.core.stocks.alphavantage

import com.ebsoftware.nero.core.stocks.StocksApi
import com.ebsoftware.nero.core.stocks.alphavantage.model.transform
import com.ebsoftware.nero.core.stocks.model.ApiQuote
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
internal class AvRetrofitStocksApi @Inject constructor(
    private val service: AvRetrofitStocksService,
    private val apiKey: String,
) : StocksApi {

    override suspend fun getQuote(ticker: String): ApiQuote =
        service.getQuery(
            symbol = ticker,
            function = FUNC_GLOBAL_QUOTE,
            apikey = apiKey
        ).transform()

    companion object {
        const val FUNC_GLOBAL_QUOTE = "GLOBAL_QUOTE"
    }
}