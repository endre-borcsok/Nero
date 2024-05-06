package com.ebsoftware.nero.core.stocks.alphavantage

import com.ebsoftware.nero.core.stocks.alphavantage.AvRetrofitStocksApi.Companion.FUNC_GLOBAL_QUOTE
import com.ebsoftware.nero.core.stocks.alphavantage.model.AvQuote
import com.ebsoftware.nero.core.stocks.alphavantage.model.transform
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.whenever
import kotlin.test.assertEquals

@RunWith(MockitoJUnitRunner::class)
class AvRetrofitStocksApiTest {

    @Mock
    private lateinit var service: AvRetrofitStocksService

    @Test
    fun `when quote requested then delegates to service`() = runTest {
        whenever(
            service.getQuery(
                symbol = "AAPL",
                function = FUNC_GLOBAL_QUOTE,
                apikey = "apikey"
            ),
        ) doReturn AvQuote.EMPTY
        assertEquals(
            expected = AvQuote.EMPTY.transform(),
            actual = AvRetrofitStocksApi(
                service = service,
                apiKey = "apikey"
            ).getQuote("AAPL")
        )
    }
}