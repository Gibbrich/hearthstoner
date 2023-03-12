package com.example.hearthstoner.data

import okhttp3.OkHttpClient
import okhttp3.Request

class ApiModule {
    companion object {
        private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com"
        private val HEADER_API_KEY = "X-RapidAPI-Key" to "75a8481d65msh0a7f1d988198a92p18d92cjsn27b9a9e8964c"
        private val HEADER_API_HOST = "X-RapidAPI-Host" to "omgvamp-hearthstone-v1.p.rapidapi.com"
    }

    private fun execute() {
        val client = OkHttpClient()

        val request = Request.Builder()
            .url("https://omgvamp-hearthstone-v1.p.rapidapi.com/cardbacks")
            .get()
            .addHeader("X-RapidAPI-Key", "75a8481d65msh0a7f1d988198a92p18d92cjsn27b9a9e8964c")
            .addHeader("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
            .build()

        val response = client.newCall(request).execute()
    }
}