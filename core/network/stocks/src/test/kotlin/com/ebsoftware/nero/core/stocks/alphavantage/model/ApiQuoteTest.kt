package com.ebsoftware.nero.core.stocks.alphavantage.model

import com.ebsoftware.nero.core.stocks.model.ApiQuote
import org.junit.Test
import kotlin.test.assertEquals

class ApiQuoteTest {

    @Test
    fun `when transformed then parameter mapping is correct`() {
        assertEquals(
            expected = ApiQuote(
                ticker = "AAPL",
                price = 2.5,
            ),
            actual = AvQuote(
                ticker = "AAPL",
                price = 2.5,
            ).transform()
        )
    }
}