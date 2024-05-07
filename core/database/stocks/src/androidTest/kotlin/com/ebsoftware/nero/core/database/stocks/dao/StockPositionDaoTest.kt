package com.ebsoftware.nero.core.database.stocks.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ebsoftware.nero.core.database.stocks.StockDatabase
import com.ebsoftware.nero.core.database.stocks.model.PositionEntity
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class StockPositionDaoTest {

    private lateinit var stockPositionDao: StockPositionDao

    private lateinit var db: StockDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context,
            StockDatabase::class.java,
        ).build()
        stockPositionDao = db.stockPositionDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun testInsertedEntriesAreListed() = runTest {
        stockPositionDao.insert(
            PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
            PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L)
        )
        assertEquals(
            expected = listOf(
                PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
                PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L)
            ),
            actual = stockPositionDao.getAll()
        )
    }

    @Test
    fun testInsertedEntriesAreListedByTicker() = runTest {
        stockPositionDao.insert(
            PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
            PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L),
            PositionEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 1L),
            PositionEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 2L),
        )
        assertEquals(
            expected = listOf(
                PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
                PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L)
            ),
            actual = stockPositionDao.getAll("AAPL")
        )
        assertEquals(
            expected = listOf(
                PositionEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 1L),
                PositionEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 2L)
            ),
            actual = stockPositionDao.getAll("IBM")
        )
    }

    @Test
    fun testInsertedEntriesCanBeDeleted() = runTest {
        stockPositionDao.insert(
            PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
        )
        stockPositionDao.delete(
            PositionEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 1L),
        )
        assertEquals(
            expected = listOf(
                PositionEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
            ),
            actual = stockPositionDao.getAll()
        )
    }
}