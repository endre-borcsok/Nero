package com.ebsoftware.nero.core.ui.stocks.model

data class SecurityMovementGainViewData(
    override val id: String,
    val ticker: String,
    val quantity: Int,
    val cost: Double,
    val gainPercent: Double,
    val gainAmount: Double,
) : SecurityMovementItem {
    companion object {
        val EMPTY = SecurityMovementGainViewData(
            id = String(),
            ticker = String(),
            quantity = Int.MAX_VALUE,
            cost = Double.MAX_VALUE,
            gainPercent = Double.MAX_VALUE,
            gainAmount = Double.MAX_VALUE,
        )
    }
}
