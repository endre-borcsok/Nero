package com.ebsoftware.nero.core.stocks.alphavantage.model

import com.ebsoftware.nero.core.stocks.model.ApiQuote
import junit.framework.TestCase.assertEquals
import org.junit.Test

class ApiQuoteTest {

    @Test
    fun `when transformed then parameter mapping is correct`() {
        assertEquals(
            ApiQuote(
                ticker = "AAPL",
                price = 2.5,
            ),
            AvQuote(
                ticker = "AAPL",
                price = 2.5,
            ).transform()
        )
    }
}