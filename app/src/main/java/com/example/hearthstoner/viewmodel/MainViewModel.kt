package com.example.hearthstoner.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.hearthstoner.di.ApiModule
import com.example.hearthstoner.data.Card
import com.example.hearthstoner.data.api.Api
import com.example.hearthstoner.di.DI
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainViewModel : ViewModel() {
    @Inject
    lateinit var api: Api

    private val _cardBacks = MutableStateFlow<List<Card>>(emptyList())
    val cardBacks: StateFlow<List<Card>> = _cardBacks

    private val _cardClickEvent =
        MutableSharedFlow<Int>(replay = 0, extraBufferCapacity = 1, BufferOverflow.DROP_OLDEST)
    val cardClickEvent: SharedFlow<Int> = _cardClickEvent

    init {
        DI.appComponent.inject(this)
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
        viewModelScope.launch {
            val cards = api.getCardBacks()
            _cardBacks.tryEmit(cards)
        }
    }
}