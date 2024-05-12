package com.ebsoftware.nero.core.data.stocks.transform

import com.ebsoftware.nero.core.database.stocks.model.SecurityMovementEntity
import com.ebsoftware.nero.core.model.SecurityMovement
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class DbToDomainTransformerTest {
    @Test
    fun `when db position list is transformed into domain position list then mapping is correct`() {
        assertEquals(
            expected =
            listOf(
                SecurityMovement(
                    ticker = "ticker",
                    quantity = 10,
                    cost = 2.0,
                    date = Date(10L),
                ),
            ),
            actual =
            listOf(
                SecurityMovementEntity(
                    ticker = "ticker",
                    quantity = 10,
                    price = 2.0,
                    dateUtcMs = 10L,
                ),
            ).transform(),
        )
    }

    @Test
    fun `when db position is transformed into domain position then mapping is correct`() {
        assertEquals(
            expected =
            SecurityMovement(
                ticker = "ticker",
                quantity = 10,
                cost = 2.0,
                date = Date(10L),
            ),
            actual =
            SecurityMovementEntity(
                ticker = "ticker",
                quantity = 10,
                price = 2.0,
                dateUtcMs = 10L,
            ).transform(),
        )
    }

    @Test
    fun `when domain position list is transformed into db position list then mapping is correct`() {
        assertEquals(
            expected =
            listOf(
                SecurityMovementEntity(
                    ticker = "ticker",
                    quantity = 10,
                    price = 2.0,
                    dateUtcMs = 10L,
                ),
            ),
            actual =
            listOf(
                SecurityMovement(
                    ticker = "ticker",
                    quantity = 10,
                    cost = 2.0,
                    date = Date(10L),
                ),
            ).transform(),
        )
    }

    @Test
    fun `when domain position is transformed into db position then mapping is correct`() {
        assertEquals(
            expected =
            SecurityMovementEntity(
                ticker = "ticker",
                quantity = 10,
                price = 2.0,
                dateUtcMs = 10L,
            ),
            actual =
            SecurityMovement(
                ticker = "ticker",
                quantity = 10,
                cost = 2.0,
                date = Date(10L),
            ).transform(),
        )
    }
}
