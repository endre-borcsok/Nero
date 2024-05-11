package com.ebsoftware.nero.core.stocks.alphavantage.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal class GlobalQuoteResponse(
    @SerialName("Global Quote") val quote: AvQuote,
)
