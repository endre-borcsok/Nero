package com.ebsoftware.nero.core.ui.stocks.model

import java.util.Date

data class SecurityMovementViewData(
    override val id: String,
    val ticker: String,
    val quantity: Int,
    val cost: Double,
    val date: Date,
) : SecurityMovementItem {
    companion object {
        val EMPTY = SecurityMovementViewData(
            id = String(),
            ticker = String(),
            quantity = Int.MAX_VALUE,
            cost = Double.MAX_VALUE,
            date = Date(),
        )
    }
}
