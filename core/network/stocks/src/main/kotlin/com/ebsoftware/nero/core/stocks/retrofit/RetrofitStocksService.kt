package com.ebsoftware.nero.core.stocks.retrofit

import okhttp3.Call
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

internal interface RetrofitStocksService

@Singleton
internal class RetrofitStocksServiceFactory @Inject constructor(
    private val okHttpClient: OkHttpClient,
) {
    operator fun invoke(): RetrofitStocksService =
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("")
            .build()
            .create(RetrofitStocksService::class.java)
}