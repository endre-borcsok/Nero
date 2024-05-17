package com.ebsoftware.nero.core.data.stocks

import com.ebsoftware.nero.core.model.SecurityMovement
import kotlinx.coroutines.flow.Flow

interface StockRepository {

    fun getSecurityMovements(): Flow<List<SecurityMovement>>

    suspend fun getSecurityMovementById(
        id: String,
    ): SecurityMovement

    fun getSecurityMovementsByTicker(
        ticker: String,
    ): Flow<List<SecurityMovement>>

    suspend fun addSecurityMovements(
        securityMovements: List<SecurityMovement>,
    )
}
