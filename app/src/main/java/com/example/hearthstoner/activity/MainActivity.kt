package com.example.hearthstoner.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.navigation.findNavController
import com.example.hearthstoner.R
import com.example.hearthstoner.fragment.CardDetailsFragment
import com.example.hearthstoner.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lifecycleScope.launch {
            viewModel.cardClickEvent.collect(::onCardClicked)
        }
    }

    private fun onCardClicked(cardId: Int) {
        findNavController(R.id.fragment_container_view).navigate(R.id.action_cardListFragment_to_cardDetailsFragment, CardDetailsFragment.getArguments(cardId))
    }
}