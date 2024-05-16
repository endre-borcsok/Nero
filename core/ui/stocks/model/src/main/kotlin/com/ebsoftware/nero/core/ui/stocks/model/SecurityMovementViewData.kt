package com.ebsoftware.nero.core.ui.stocks.model

import java.util.Date

data class SecurityMovementViewData(
    val ticker: String,
    val quantity: Int,
    val cost: Double,
    val date: Date,
) {
    companion object {
        val EMPTY = SecurityMovementViewData(
            ticker = String(),
            quantity = Int.MAX_VALUE,
            cost = Double.MAX_VALUE,
            date = Date(),
        )
    }
}
