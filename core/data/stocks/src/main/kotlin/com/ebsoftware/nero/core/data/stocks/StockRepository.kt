package com.ebsoftware.nero.core.data.stocks

import com.ebsoftware.nero.core.model.StockPosition
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    fun getPositions(): Flow<List<StockPosition>>

    suspend fun addPositions(positions: List<StockPosition>)
}