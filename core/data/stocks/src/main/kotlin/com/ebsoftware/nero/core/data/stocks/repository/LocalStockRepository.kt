package com.ebsoftware.nero.core.data.stocks.repository

import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.data.stocks.transform.transform
import com.ebsoftware.nero.core.database.stocks.dao.StockPositionDao
import com.ebsoftware.nero.core.database.stocks.model.SecurityMovementEntity
import com.ebsoftware.nero.core.model.SecurityMovement
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalStockRepository @Inject constructor(
    private val stockPositionDao: StockPositionDao,
) : StockRepository {

    override fun getSecurityMovements(): Flow<List<SecurityMovement>> =
        stockPositionDao.getAll().map(List<SecurityMovementEntity>::transform)

    override suspend fun getSecurityMovementById(
        id: String,
    ): SecurityMovement = stockPositionDao.getById(id).transform()

    override fun getSecurityMovementsByTicker(
        ticker: String,
    ): Flow<List<SecurityMovement>> = stockPositionDao.getAll(ticker).map(List<SecurityMovementEntity>::transform)

    override suspend fun addSecurityMovements(
        securityMovements: List<SecurityMovement>,
    ) {
        stockPositionDao.insert(*securityMovements.map(SecurityMovement::transform).toTypedArray())
    }
}
