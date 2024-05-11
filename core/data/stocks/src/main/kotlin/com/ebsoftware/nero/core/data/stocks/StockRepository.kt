package com.ebsoftware.nero.core.data.stocks

import com.ebsoftware.nero.core.model.SecurityMovement
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    fun getPositions(): Flow<List<SecurityMovement>>

    suspend fun addPositions(positions: List<SecurityMovement>)
}