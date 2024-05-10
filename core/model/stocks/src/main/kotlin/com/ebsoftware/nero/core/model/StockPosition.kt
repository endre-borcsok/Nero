package com.ebsoftware.nero.core.model

import java.util.Date

data class StockPosition(
    val ticker: String,
    val quantity: Int,
    val cost: Double,
    val date: Date,
) {
    companion object {
        val EMPTY = StockPosition(
            ticker = String(),
            quantity = Int.MAX_VALUE,
            cost = Double.MAX_VALUE,
            date = Date(),
        )
    }
}