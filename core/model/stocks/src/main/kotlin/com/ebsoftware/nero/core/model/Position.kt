package com.ebsoftware.nero.core.model

import java.util.Date

data class Position(
    val count: Int,
    val price: Double,
    val date: Date,
) {
    companion object {
        val EMPTY = Position(
            count = Int.MAX_VALUE,
            price = Double.MAX_VALUE,
            date = Date(),
        )
    }
}