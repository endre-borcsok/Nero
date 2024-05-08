package com.ebsoftware.nero.core.model

import java.util.Date

data class StockPosition(
    val ticker: String,
    val count: Int,
    val price: Double,
    val date: Date,
) {
    companion object {
        val EMPTY = StockPosition(
            ticker = String(),
            count = Int.MAX_VALUE,
            price = Double.MAX_VALUE,
            date = Date(),
        )
    }
}