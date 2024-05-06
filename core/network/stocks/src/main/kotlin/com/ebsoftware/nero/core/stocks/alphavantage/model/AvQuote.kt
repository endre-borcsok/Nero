package com.ebsoftware.nero.core.stocks.alphavantage.model

import com.ebsoftware.nero.core.stocks.model.ApiQuote

internal data class AvQuote(
    val ticker: String,
    val price: Double,
) {
    companion object {
        val EMPTY = AvQuote(
            ticker = "ticker",
            price = 1.0,
        )
    }
}

internal fun AvQuote.transform() =
    ApiQuote(
        ticker = ticker,
        price = price,
    )