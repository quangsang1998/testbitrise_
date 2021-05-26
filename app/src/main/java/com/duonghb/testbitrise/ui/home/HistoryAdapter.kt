package com.duonghb.testbitrise.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duonghb.testbitrise.databinding.ItemHistoryBinding
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData
import com.squareup.picasso.Picasso

class HistoryAdapter(
    private val clickHistoryItemCallback: ((historyModelData: HistoryModelData) -> Unit)
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val listNewsHistory = mutableListOf<HistoryModelData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val news: HistoryModelData = listNewsHistory.get(position)
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return listNewsHistory.size
    }

    fun setHistoryItems(items: List<HistoryModelData>) {
        listNewsHistory.clear()
        listNewsHistory.addAll(items)
        notifyDataSetChanged()
    }

    inner class HistoryViewHolder(itemView: ItemHistoryBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemHistoryBinding = itemView

        fun bind(news: HistoryModelData) {
            binding.modelHistory = news

            binding.root.setOnClickListener {
                clickHistoryItemCallback.invoke(news)
            }
            Picasso.get().load(news.imageUrl).into(binding.newsHistoryImageView)
        }
    }
}
