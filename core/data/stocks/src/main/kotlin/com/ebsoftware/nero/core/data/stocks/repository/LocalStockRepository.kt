package com.ebsoftware.nero.core.data.stocks.repository

import com.ebsoftware.nero.core.data.stocks.StockRepository
import com.ebsoftware.nero.core.data.stocks.transform.transform
import com.ebsoftware.nero.core.database.stocks.dao.StockPositionDao
import com.ebsoftware.nero.core.database.stocks.model.PositionEntity
import com.ebsoftware.nero.core.model.StockPosition
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LocalStockRepository @Inject constructor(
    private val stockPositionDao: StockPositionDao,
) : StockRepository {

    override fun getPositions(): Flow<List<StockPosition>> =
        stockPositionDao.getAll().map(List<PositionEntity>::transform)

    override suspend fun addPositions(positions: List<StockPosition>) {
        stockPositionDao.insert(*positions.map(StockPosition::transform).toTypedArray())
    }
}