package com.example.hearthstoner.fragment

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hearthstoner.R
import com.example.hearthstoner.data.Card
import com.example.hearthstoner.databinding.FragmentCardListItemBinding

class CardRecyclerViewAdapter(
    private var items: List<Card>,
    private val onClickListener: (Int) -> Unit
) : RecyclerView.Adapter<CardRecyclerViewAdapter.ViewHolder>() {

    fun updateItems(newItems: List<Card>) {
        val callback = CardDiffCallback(items, newItems)
        val diff = DiffUtil.calculateDiff(callback)
        items = newItems
        diff.dispatchUpdatesTo(this)
    }

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

    inner class ViewHolder(binding: FragmentCardListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val image: ImageView = binding.cardImage
    }
}

class CardDiffCallback(
    private val old: List<Card>,
    private val new: List<Card>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = old.size

    override fun getNewListSize(): Int = new.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition].cardBackId == new[newItemPosition].cardBackId

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
        old[oldItemPosition] == new[newItemPosition]
}