package com.ebsoftware.nero.testing.android.repository

import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.model.SecurityMovement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class TestStocksRepository @Inject constructor() : StockRepository {

    private val securityMovements = flowOf(
        listOf(
            SecurityMovement.EMPTY.copy(
                id = "aapl-id-1",
                ticker = "AAPL",
                quantity = 3,
                cost = 162.5,
            ),
            SecurityMovement.EMPTY.copy(
                id = "aapl-id-2",
                ticker = "AAPL",
                quantity = 2,
                cost = 136.5,
            ),
            SecurityMovement.EMPTY.copy(
                id = "ibm-id-1",
                ticker = "IBM",
                quantity = 3,
                cost = 121.5,
            ),
            SecurityMovement.EMPTY.copy(
                id = "ibm-id-2",
                ticker = "IBM",
                quantity = 2,
                cost = 123.5,
            ),
        ),
    )

    override fun getSecurityMovements(): Flow<List<SecurityMovement>> = securityMovements

    override suspend fun getSecurityMovementById(
        id: String,
    ) = securityMovements.first().find { it.id == id } ?: throw IllegalArgumentException()

    override fun getSecurityMovementsByTicker(
        ticker: String,
    ): Flow<List<SecurityMovement>> = securityMovements.map { it.filter { it.ticker == ticker } }

    override suspend fun addSecurityMovements(
        securityMovements: List<SecurityMovement>,
    ) = Unit
}
