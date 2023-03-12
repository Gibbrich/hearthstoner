package com.example.hearthstoner.data.api

import com.example.hearthstoner.data.Card
import retrofit2.http.GET

interface Api {
    @GET("cardbacks")
    suspend fun getCardBacks(): List<Card>
}