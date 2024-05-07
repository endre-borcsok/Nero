package com.ebsoftware.nero.core.database.stocks.model

import java.util.Date

data class PositionEntity(
    val ticker: String,
    val count: Int,
    val price: Double,
    val date: Date,
) {
    companion object {
        val EMPTY = PositionEntity(
            ticker = String(),
            count = Int.MAX_VALUE,
            price = Double.MAX_VALUE,
            date = Date(),
        )
    }
}