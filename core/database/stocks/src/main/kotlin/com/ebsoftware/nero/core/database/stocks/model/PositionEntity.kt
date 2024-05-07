package com.ebsoftware.nero.core.database.stocks.model

import androidx.room.Entity

@Entity(primaryKeys = ["ticker", "dateUtcMs"])
data class PositionEntity(
    val ticker: String,
    val dateUtcMs: Long,
    val count: Int,
    val price: Double,
) {
    companion object {
        val EMPTY = PositionEntity(
            ticker = String(),
            count = Int.MAX_VALUE,
            price = Double.MAX_VALUE,
            dateUtcMs = Long.MAX_VALUE,
        )
    }
}