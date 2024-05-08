package com.ebsoftware.nero.core.data.stocks.transform

import com.ebsoftware.nero.core.database.stocks.model.PositionEntity
import com.ebsoftware.nero.core.model.StockPosition
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class DbToDomainTransformerTest {

    @Test
    fun `when db position list is transformed into domain position list then mapping is correct`() {
        assertEquals(
            expected = listOf(
                StockPosition(
                    ticker = "ticker",
                    count = 10,
                    price = 2.0,
                    date = Date(10L),
                )
            ),
            actual = listOf(
                PositionEntity(
                    ticker = "ticker",
                    count = 10,
                    price = 2.0,
                    dateUtcMs = 10L,
                )
            ).transform()
        )
    }

    @Test
    fun `when db position is transformed into domain position then mapping is correct`() {
        assertEquals(
            expected = StockPosition(
                ticker = "ticker",
                count = 10,
                price = 2.0,
                date = Date(10L),
            ),
            actual = PositionEntity(
                ticker = "ticker",
                count = 10,
                price = 2.0,
                dateUtcMs = 10L,
            ).transform()
        )
    }

    @Test
    fun `when domain position list is transformed into db position list then mapping is correct`() {
        assertEquals(
            expected = listOf(
                PositionEntity(
                    ticker = "ticker",
                    count = 10,
                    price = 2.0,
                    dateUtcMs = 10L
                )
            ),
            actual = listOf(
                StockPosition(
                    ticker = "ticker",
                    count = 10,
                    price = 2.0,
                    date = Date(10L),
                )
            ).transform()
        )
    }

    @Test
    fun `when domain position is transformed into db position then mapping is correct`() {
        assertEquals(
            expected = PositionEntity(
                ticker = "ticker",
                count = 10,
                price = 2.0,
                dateUtcMs = 10L,
            ),
            actual = StockPosition(
                ticker = "ticker",
                count = 10,
                price = 2.0,
                date = Date(10L),
            ).transform()
        )
    }
}