package com.example.hearthstoner.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearthstoner.R
import com.example.hearthstoner.data.Card
import com.example.hearthstoner.databinding.FragmentCardBinding
import com.example.hearthstoner.fragment.placeholder.PlaceholderContent.PlaceholderItem

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
        Glide
            .with(holder.image.context)
            .load(item.imageUrl)
            .centerCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(binding: FragmentCardBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.cardImage
    }
}