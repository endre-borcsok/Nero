package com.ebsoftware.nero.core.stocks

import com.ebsoftware.nero.core.stocks.model.ApiQuote

interface StocksApi {

    suspend fun getQuote(ticker: String): ApiQuote
}