package com.ebsoftware.nero.core.stocks.alphavantage

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
    ): GlobalQuoteResponse
}

@Singleton
internal class AvRetrofitStocksServiceFactory @Inject constructor(
    private val okHttpClientBuilder: OkHttpClient.Builder,
    private val baseUrl: AvBaseUrl,
    private val apiKeyInterceptor: AvApiKeyInterceptor,
    private val serializer: Json,
) {
    operator fun invoke(): AvRetrofitStocksService = Retrofit.Builder()
        .client(
            okHttpClientBuilder
                .addInterceptor(apiKeyInterceptor)
                .build(),
        )
        .baseUrl(baseUrl.value)
        .addConverterFactory(serializer.asConverterFactory("application/json".toMediaType()))
        .build()
        .create(AvRetrofitStocksService::class.java)
}
