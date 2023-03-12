package com.example.hearthstoner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearthstoner.data.ApiModule
import com.example.hearthstoner.data.Card
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    private val _cardBacks = MutableSharedFlow<List<Card>>()
    val cardBacks: SharedFlow<List<Card>> = _cardBacks

    init {
        fetchCardBacks()
    }

    private fun fetchCardBacks() {
        val apiModule = ApiModule()
        val loggingInterceptor = apiModule.provideLoggingInterceptor()
        val client = apiModule.provideClient(loggingInterceptor)
        val retrofit = apiModule.provideRetrofit(client)
        val api = apiModule.provideApi(retrofit)

        viewModelScope.launch {
            val cards = api.getCardBacks()
            _cardBacks.emit(cards)
        }
    }
}