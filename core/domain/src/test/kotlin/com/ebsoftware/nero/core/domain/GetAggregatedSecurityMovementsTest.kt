package com.ebsoftware.nero.core.domain

import com.ebsoftware.nero.core.model.SecurityMovement
import org.junit.Test
import java.util.Date
import kotlin.test.assertEquals

class GetAggregatedSecurityMovementsTest {

    @Test
    fun `when invoked then identical security movements get aggregated`() {
        assertEquals(
            expected = listOf(
                SecurityMovement.EMPTY.copy(
                    ticker = "AAPL",
                    cost = 3.0,
                    quantity = 3,
                    date = Date(2L),
                ),
                SecurityMovement.EMPTY.copy(
                    ticker = "IBM",
                    cost = 1.0,
                    quantity = 1,
                    date = Date(1L),
                ),
            ),
            actual = GetAggregatedSecurityMovements()
                .invoke(
                    securityMovements = listOf(
                        SecurityMovement.EMPTY.copy(
                            ticker = "AAPL",
                            cost = 1.0,
                            quantity = 1,
                            date = Date(1L),
                        ),
                        SecurityMovement.EMPTY.copy(
                            ticker = "IBM",
                            cost = -1.0,
                            quantity = -1,
                            date = Date(1L),
                        ),
                        SecurityMovement.EMPTY.copy(
                            ticker = "AAPL",
                            cost = 2.0,
                            quantity = 2,
                            date = Date(2L),
                        ),
                        SecurityMovement.EMPTY.copy(
                            ticker = "IBM",
                            cost = 2.0,
                            quantity = 2,
                            date = Date(1L),
                        ),
                    ),
                ),
        )
    }
}
