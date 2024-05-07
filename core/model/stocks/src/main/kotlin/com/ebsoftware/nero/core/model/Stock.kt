package com.ebsoftware.nero.core.model

data class Stock(
    val ticker: String,
    val positions: List<Position>,
) {
    companion object {
        val EMPTY = Stock(
            ticker = "ticker",
            positions = listOf(Position.EMPTY)
        )
    }
}