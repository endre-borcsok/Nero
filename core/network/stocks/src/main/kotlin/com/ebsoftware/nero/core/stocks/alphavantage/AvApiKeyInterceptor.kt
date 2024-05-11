package com.ebsoftware.nero.core.stocks.alphavantage

import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

internal class AvApiKeyInterceptor
    @Inject
    constructor(
        private val apiKey: String,
    ) : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response =
            chain.proceed(
                chain.request()
                    .newBuilder()
                    .url(
                        chain.request()
                            .url
                            .newBuilder()
                            .addQueryParameter("apikey", apiKey)
                            .build(),
                    )
                    .build(),
            )
    }
