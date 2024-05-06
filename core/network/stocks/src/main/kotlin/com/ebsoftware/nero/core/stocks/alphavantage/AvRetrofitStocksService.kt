package com.ebsoftware.nero.core.stocks.alphavantage

import com.ebsoftware.nero.core.stocks.alphavantage.model.AvQuote
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

internal interface AvRetrofitStocksService {

    @GET("/query")
    suspend fun getQuery(
        @Query("symbol") symbol: String,
        @Query("function") function: String,
        @Query("apikey") apikey: String,
    ) : AvQuote
}

@Singleton
internal class AvRetrofitStocksServiceFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: AvBaseUrl,
) {
    operator fun invoke(): AvRetrofitStocksService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl.name)
            .build()
            .create(AvRetrofitStocksService::class.java)
}