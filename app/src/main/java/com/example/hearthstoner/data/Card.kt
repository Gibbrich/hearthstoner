package com.example.hearthstoner.data

import com.google.gson.annotations.SerializedName

data class Card(
    @SerializedName("cardBackId")
    val cardBackId: Int,

    @SerializedName("name")
    val name: String,

    @SerializedName("description")
    val description: String,

    @SerializedName("img")
    val imageUrl: String
)