package com.ebsoftware.nero.core.stocks.alphavantage

import com.ebsoftware.nero.core.stocks.alphavantage.model.AvQuote
import com.ebsoftware.nero.core.stocks.alphavantage.model.GlobalQuoteResponse
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Inject
import javax.inject.Singleton

internal interface AvRetrofitStocksService {

    @GET("/query")
    suspend fun getQuery(
        @Query("function") function: String,
        @Query("symbol") symbol: String,
        @Query("apikey") apikey: String,
    ) : GlobalQuoteResponse
}

@Singleton
internal class AvRetrofitStocksServiceFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
    private val baseUrl: AvBaseUrl,
    private val serializer: Json,
) {
    operator fun invoke(): AvRetrofitStocksService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(baseUrl.value)
            .addConverterFactory(serializer.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(AvRetrofitStocksService::class.java)
}