package com.example.shemajamebeli9.ui.home

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.shemajamebeli9.R
import com.example.shemajamebeli9.databinding.CardItemBinding
import com.example.shemajamebeli9.model.CardItem

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.CardItemViewHolder>() {

    var cardList: List<CardItem> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = CardItemViewHolder(
        CardItemBinding.inflate(LayoutInflater.from(parent.context))
    )

    override fun onBindViewHolder(holder: CardItemViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = cardList.size

    inner class CardItemViewHolder(private val binding: CardItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        lateinit var curItem: CardItem

        fun bind() {
            curItem = cardList[adapterPosition]
            binding.priceTxtView.text = curItem.price
            binding.titleTxtView.text = curItem.title
            Glide.with(itemView.context).load(curItem.cover).into(binding.imageView)
            if (!curItem.liked)
                binding.favouriteFloatingBtn.setImageResource(R.drawable.ic_heart_not_fav)
        }
    }

}