package com.example.hearthstoner.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.example.hearthstoner.R
import com.example.hearthstoner.data.Card
import com.example.hearthstoner.viewmodel.MainViewModel
import kotlinx.coroutines.launch

/**
 * A fragment representing a list of Items.
 */
class CardListFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private val cardsAdapter = CardRecyclerViewAdapter(mutableListOf(), ::onCardClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_card_list, container, false) as RecyclerView
        return view.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = cardsAdapter
        }
    }

    private fun onCardClick(cardId: Int) = viewModel.onCardClick(cardId)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.cardBacks.collect(::handleCardBacks)
        }
    }

    private fun handleCardBacks(cards: List<Card> ) {
        cardsAdapter.items = cards
        cardsAdapter.notifyDataSetChanged()
    }

    companion object {
        @JvmStatic
        fun newInstance(columnCount: Int) = CardListFragment()
    }
}