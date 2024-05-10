package com.ebsoftware.nero.core.data.stocks.repository

import com.ebsoftware.nero.core.data.stocks.transform.transform
import com.ebsoftware.nero.core.database.stocks.dao.StockPositionDao
import com.ebsoftware.nero.core.model.SecurityMovement
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

@RunWith(MockitoJUnitRunner::class)
class LocalStockRepositoryTest {

    @Mock
    private lateinit var stockPositionDao: StockPositionDao

    @Test
    fun `when stream of positions requested then it is taken from the dao`() = runTest {
        whenever(stockPositionDao.getAll()) doReturn emptyFlow()
        LocalStockRepository(
            stockPositionDao = stockPositionDao,
        ).getPositions()
        verify(stockPositionDao).getAll()
    }

    @Test
    fun `when stream of positions are added then they are added with the dao`() = runTest {
        val positions = listOf(SecurityMovement.EMPTY, SecurityMovement.EMPTY)
        LocalStockRepository(
            stockPositionDao = stockPositionDao,
        ).addPositions(positions)
        verify(stockPositionDao).insert(*positions.map(SecurityMovement::transform).toTypedArray())
    }
}