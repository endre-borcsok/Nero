package com.ebsoftware.nero.core.stocks.alphavantage.model

import com.ebsoftware.nero.core.stocks.model.ApiQuote
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class AvQuote(
    @SerialName("01. symbol") val ticker: String,
    @SerialName("05. price") val price: String,
) {
    companion object {
        val EMPTY =
            AvQuote(
                ticker = "ticker",
                price = "1.0",
            )
    }
}

internal fun AvQuote.transform() =
    ApiQuote(
        ticker = ticker,
        price = price.toDouble(),
    )
