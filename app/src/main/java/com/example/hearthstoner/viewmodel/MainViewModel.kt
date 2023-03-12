package com.example.hearthstoner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearthstoner.data.ApiModule
import com.example.hearthstoner.data.Card
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    private val _cardBacks = MutableStateFlow<List<Card>>(emptyList())
    val cardBacks: StateFlow<List<Card>> = _cardBacks

    private val _cardClickEvent =
        MutableSharedFlow<Int>(replay = 0, extraBufferCapacity = 1, BufferOverflow.DROP_OLDEST)
    val cardClickEvent: SharedFlow<Int> = _cardClickEvent

    init {
        fetchCardBacks()
    }

    fun onCardClick(cardId: Int) {
        _cardClickEvent.tryEmit(cardId)
    }

    fun getCardById(cardId: Int): Card =
        cardBacks
            .replayCache
            .first()
            .first { it.cardBackId == cardId }

    private fun fetchCardBacks() {
        val apiModule = ApiModule()
        val loggingInterceptor = apiModule.provideLoggingInterceptor()
        val client = apiModule.provideClient(loggingInterceptor)
        val retrofit = apiModule.provideRetrofit(client)
        val api = apiModule.provideApi(retrofit)

        viewModelScope.launch {
            val cards = api.getCardBacks()
            _cardBacks.tryEmit(cards)
        }
    }
}