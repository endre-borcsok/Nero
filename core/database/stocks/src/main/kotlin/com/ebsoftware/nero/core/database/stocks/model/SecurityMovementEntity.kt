package com.ebsoftware.nero.core.database.stocks.model

import androidx.room.Entity

@Entity(primaryKeys = ["id"])
data class SecurityMovementEntity(
    val id: String,
    val ticker: String,
    val dateUtcMs: Long,
    val quantity: Int,
    val price: Double,
) {
    companion object {
        val EMPTY =
            SecurityMovementEntity(
                id = String(),
                ticker = String(),
                quantity = Int.MAX_VALUE,
                price = Double.MAX_VALUE,
                dateUtcMs = Long.MAX_VALUE,
            )
    }
}
