package com.ebsoftware.nero.core.database.stocks.dao

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.ebsoftware.nero.core.database.stocks.StockDatabase
import com.ebsoftware.nero.core.database.stocks.model.SecurityMovementEntity
import kotlinx.coroutines.flow.first
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
        db =
            Room.inMemoryDatabaseBuilder(
                context,
                StockDatabase::class.java,
            ).build()
        stockPositionDao = db.stockPositionDao()
    }

    @After
    fun closeDb() = db.close()

    @Test
    fun testInsertedEntriesAreListed() =
        runTest {
            stockPositionDao.insert(
                SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
                SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L),
            )
            assertEquals(
                expected =
                    listOf(
                        SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
                        SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L),
                    ),
                actual = stockPositionDao.getAll().first(),
            )
        }

    @Test
    fun testInsertedEntriesAreListedByTicker() =
        runTest {
            stockPositionDao.insert(
                SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
                SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L),
                SecurityMovementEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 1L),
                SecurityMovementEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 2L),
            )
            assertEquals(
                expected =
                    listOf(
                        SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
                        SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 2L),
                    ),
                actual = stockPositionDao.getAll("AAPL").first(),
            )
            assertEquals(
                expected =
                    listOf(
                        SecurityMovementEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 1L),
                        SecurityMovementEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 2L),
                    ),
                actual = stockPositionDao.getAll("IBM").first(),
            )
        }

    @Test
    fun testInsertedEntriesCanBeDeleted() =
        runTest {
            stockPositionDao.insert(
                SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
            )
            stockPositionDao.delete(
                SecurityMovementEntity.EMPTY.copy(ticker = "IBM", dateUtcMs = 1L),
            )
            assertEquals(
                expected =
                    listOf(
                        SecurityMovementEntity.EMPTY.copy(ticker = "AAPL", dateUtcMs = 1L),
                    ),
                actual = stockPositionDao.getAll().first(),
            )
        }
}
