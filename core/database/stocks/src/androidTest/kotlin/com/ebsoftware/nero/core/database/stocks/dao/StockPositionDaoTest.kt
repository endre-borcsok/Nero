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
import kotlin.test.assertTrue

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
            SecurityMovementEntity.EMPTY.copy(id = "A-AAPL", ticker = "AAPL"),
            SecurityMovementEntity.EMPTY.copy(id = "B-AAPL", ticker = "AAPL"),
        )
        assertEquals(
            expected = listOf(
                SecurityMovementEntity.EMPTY.copy(id = "A-AAPL", ticker = "AAPL"),
                SecurityMovementEntity.EMPTY.copy(id = "B-AAPL", ticker = "AAPL"),
            ),
            actual = stockPositionDao.getAll().first(),
        )
    }

    @Test
    fun testInsertedEntriesAreListedByTicker() = runTest {
        stockPositionDao.insert(
            SecurityMovementEntity.EMPTY.copy(id = "A-AAPL", ticker = "AAPL"),
            SecurityMovementEntity.EMPTY.copy(id = "B-AAPL", ticker = "AAPL"),
            SecurityMovementEntity.EMPTY.copy(id = "A-IBM", ticker = "IBM"),
            SecurityMovementEntity.EMPTY.copy(id = "B-IBM", ticker = "IBM"),
        )
        assertEquals(
            expected = listOf(
                SecurityMovementEntity.EMPTY.copy(id = "A-AAPL", ticker = "AAPL"),
                SecurityMovementEntity.EMPTY.copy(id = "B-AAPL", ticker = "AAPL"),
            ),
            actual = stockPositionDao.getAll("AAPL").first(),
        )
        assertEquals(
            expected = listOf(
                SecurityMovementEntity.EMPTY.copy(id = "A-IBM", ticker = "IBM"),
                SecurityMovementEntity.EMPTY.copy(id = "B-IBM", ticker = "IBM"),
            ),
            actual = stockPositionDao.getAll("IBM").first(),
        )
    }

    @Test
    fun testInsertedEntriesCanBeDeleted() = runTest {
        stockPositionDao.insert(
            SecurityMovementEntity.EMPTY.copy(id = "AAPL", ticker = "AAPL"),
            SecurityMovementEntity.EMPTY.copy(id = "IBM", ticker = "IBM"),
        )
        assertTrue(stockPositionDao.getAll().first().size == 2)
        stockPositionDao.delete(
            SecurityMovementEntity.EMPTY.copy(id = "IBM", ticker = "IBM"),
        )
        assertTrue(stockPositionDao.getAll().first().size == 1)
    }

    @Test
    fun testInsertedEntriesCanBeReplaced() = runTest {
        stockPositionDao.insert(
            SecurityMovementEntity.EMPTY.copy(id = "AAPL", ticker = "AAPL", quantity = 2),
            SecurityMovementEntity.EMPTY.copy(id = "IBM", ticker = "IBM", quantity = 2),
        )
        assertTrue(stockPositionDao.getAll().first().size == 2)
        stockPositionDao.insert(
            SecurityMovementEntity.EMPTY.copy(id = "AAPL", ticker = "AAPL", quantity = 3),
            SecurityMovementEntity.EMPTY.copy(id = "IBM", ticker = "IBM", quantity = 3),
        )
        assertEquals(
            expected = listOf(
                SecurityMovementEntity.EMPTY.copy(id = "AAPL", ticker = "AAPL", quantity = 3),
                SecurityMovementEntity.EMPTY.copy(id = "IBM", ticker = "IBM", quantity = 3),
            ),
            actual = stockPositionDao.getAll().first(),
        )
    }
}
