package com.example.hearthstoner.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearthstoner.R
import com.example.hearthstoner.data.Card
import com.example.hearthstoner.databinding.FragmentCardListItemBinding

class CardRecyclerViewAdapter(
    var items: List<Card>,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentCardListItemBinding.inflate(
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
            .placeholder(R.drawable.ic_launcher_foreground)
            .into(holder.image)

        holder.itemView.setOnClickListener {
            onClickListener.invoke(item.cardBackId)
        }
    }

    override fun getItemCount(): Int = items.size

    inner class ViewHolder(binding: FragmentCardListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.cardImage
    }
}