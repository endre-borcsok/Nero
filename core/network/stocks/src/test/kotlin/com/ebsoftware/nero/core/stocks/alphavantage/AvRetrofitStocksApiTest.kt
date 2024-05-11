package com.ebsoftware.nero.core.stocks.alphavantage

import com.ebsoftware.nero.core.stocks.alphavantage.AvRetrofitStocksApi.Companion.FUNC_GLOBAL_QUOTE
import com.ebsoftware.nero.core.stocks.alphavantage.model.AvQuote
import com.ebsoftware.nero.core.stocks.alphavantage.model.GlobalQuoteResponse
import com.ebsoftware.nero.core.stocks.alphavantage.model.transform
import com.ebsoftware.nero.core.stocks.di.AvStocksNetworkModule
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.Ignore
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
    fun `when quote requested then delegates to service`() =
        runTest {
            whenever(
                service.getQuery(
                    symbol = "AAPL",
                    function = FUNC_GLOBAL_QUOTE,
                ),
            ) doReturn
                GlobalQuoteResponse(
                    quote = AvQuote.EMPTY,
                )
            assertEquals(
                expected = AvQuote.EMPTY.transform(),
                actual =
                    AvRetrofitStocksApi(
                        service = service,
                    ).getQuote("AAPL"),
            )
        }

    @Test
    @Ignore("Utility test function to assert API is operating")
    fun `when API request is sent with demo key then response is serialised`() =
        runTest {
            AvRetrofitStocksApi(
                service =
                    AvRetrofitStocksServiceFactory(
                        okHttpClientBuilder =
                            OkHttpClient.Builder()
                                .addInterceptor(
                                    HttpLoggingInterceptor()
                                        .apply {
                                            setLevel(HttpLoggingInterceptor.Level.BODY)
                                        },
                                ),
                        baseUrl = AvStocksNetworkModule.provideRetrofitBaseUrl(),
                        apiKeyInterceptor = AvApiKeyInterceptor(AvStocksNetworkModule.provideApiKey()),
                        serializer = AvStocksNetworkModule.providesNetworkJson(),
                    ).invoke(),
            )
                .getQuote("IBM")
                .also(::println)
        }
}
