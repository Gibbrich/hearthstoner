package com.example.hearthstoner.data

import android.util.Log
import com.example.hearthstoner.data.api.Api
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiModule {
    companion object {
        private const val BASE_URL = "https://omgvamp-hearthstone-v1.p.rapidapi.com"
        private val HEADER_API_KEY = "X-RapidAPI-Key" to "75a8481d65msh0a7f1d988198a92p18d92cjsn27b9a9e8964c"
        private val HEADER_API_HOST = "X-RapidAPI-Host" to "omgvamp-hearthstone-v1.p.rapidapi.com"
    }

//    private fun execute() {
//        val client = OkHttpClient()
//
//        val request = Request.Builder()
//            .url("https://omgvamp-hearthstone-v1.p.rapidapi.com/cardbacks")
//            .get()
//            .addHeader("X-RapidAPI-Key", "75a8481d65msh0a7f1d988198a92p18d92cjsn27b9a9e8964c")
//            .addHeader("X-RapidAPI-Host", "omgvamp-hearthstone-v1.p.rapidapi.com")
//            .build()
//
//        val response = client.newCall(request).execute()
//    }

    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)

    fun provideClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .addInterceptor(HeaderInterceptor())
            .build()
    }

    fun provideLoggingInterceptor(): HttpLoggingInterceptor {
        val logger = HttpLoggingInterceptor.Logger { message -> Log.d("HttpLoggingInterceptor", message) }
        val loggingInterceptor = HttpLoggingInterceptor(logger)
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return loggingInterceptor
    }

    fun provideRetrofit(client: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(client)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}