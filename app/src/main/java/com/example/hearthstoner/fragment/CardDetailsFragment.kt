package com.example.hearthstoner.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.bumptech.glide.Glide
import com.example.hearthstoner.R
import com.example.hearthstoner.databinding.FragmentCardDetailsBinding
import com.example.hearthstoner.viewmodel.MainViewModel

private const val CARD_ID = "card_id"

class CardDetailsFragment : Fragment() {
    private val viewModel: MainViewModel by activityViewModels()
    private var cardId: Int = -1

    private var _binding: FragmentCardDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding
        get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cardId = it.getInt(CARD_ID)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCardDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val card = viewModel.getCardById(cardId)
        Glide
            .with(this)
            .load(card.imageUrl)
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(binding.cardDetailsImage)

        binding.cardDetailsTitle.text = card.name
        binding.cardDetailsDescription.text = card.description
    }

    companion object {
        @JvmStatic
        fun newInstance(cardId: Int) =
            CardDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(CARD_ID, cardId)
                }
            }
    }
}