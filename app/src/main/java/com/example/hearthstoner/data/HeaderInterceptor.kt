package com.example.hearthstoner.data

import okhttp3.Headers.Companion.toHeaders
import okhttp3.Interceptor
import okhttp3.Response

class HeaderInterceptor: Interceptor {
    private val headers = mutableMapOf(
        "X-RapidAPI-Key" to "75a8481d65msh0a7f1d988198a92p18d92cjsn27b9a9e8964c",
        "X-RapidAPI-Host" to "omgvamp-hearthstone-v1.p.rapidapi.com"
    )

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.request()
            .newBuilder()
            .headers(headers.toHeaders())
            .build()
            .let(chain::proceed)
    }
}