package com.example.hearthstoner.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.hearthstoner.R
import com.example.hearthstoner.data.Card
import com.example.hearthstoner.databinding.FragmentCardBinding
import com.example.hearthstoner.fragment.placeholder.PlaceholderContent.PlaceholderItem

/**
 * [RecyclerView.Adapter] that can display a [PlaceholderItem].
 * TODO: Replace the implementation with code for your data type.
 */
class CardRecyclerViewAdapter(
    var items: List<Card>
) : RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCardBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        // todo - load card images
        holder.image.setImageResource(R.drawable.ic_launcher_foreground)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(binding: FragmentCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.cardImage
    }
}