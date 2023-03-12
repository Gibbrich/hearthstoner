package com.example.hearthstoner.activity

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.add
import androidx.fragment.app.commit
import androidx.lifecycle.lifecycleScope
import com.example.hearthstoner.R
import com.example.hearthstoner.fragment.CardDetailsFragment
import com.example.hearthstoner.fragment.CardListFragment
import com.example.hearthstoner.viewmodel.MainViewModel
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(R.layout.activity_main) {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                setReorderingAllowed(true)
                add<CardListFragment>(R.id.fragment_container_view)
            }
        }

        lifecycleScope.launch {
            viewModel.cardClickEvent.collect(::onCardClicked)
        }
    }

    private fun onCardClicked(cardId: Int) {
        supportFragmentManager.commit {
            replace(R.id.fragment_container_view, CardDetailsFragment.newInstance(cardId))
            setReorderingAllowed(true)
            addToBackStack(null)
        }
    }
}