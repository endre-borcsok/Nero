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
            expected = listOf(
                SecurityMovement(
                    id = "id",
                    ticker = "ticker",
                    quantity = 10,
                    cost = 2.0,
                    date = Date(10L),
                ),
            ),
            actual = listOf(
                SecurityMovementEntity(
                    id = "id",
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
            expected = SecurityMovement(
                id = "id",
                ticker = "ticker",
                quantity = 10,
                cost = 2.0,
                date = Date(10L),
            ),
            actual = SecurityMovementEntity(
                id = "id",
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
            expected = listOf(
                SecurityMovementEntity(
                    id = "id",
                    ticker = "ticker",
                    quantity = 10,
                    price = 2.0,
                    dateUtcMs = 10L,
                ),
            ),
            actual = listOf(
                SecurityMovement(
                    id = "id",
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
            expected = SecurityMovementEntity(
                id = "id",
                ticker = "ticker",
                quantity = 10,
                price = 2.0,
                dateUtcMs = 10L,
            ),
            actual = SecurityMovement(
                id = "id",
                ticker = "ticker",
                quantity = 10,
                cost = 2.0,
                date = Date(10L),
            ).transform(),
        )
    }
}
