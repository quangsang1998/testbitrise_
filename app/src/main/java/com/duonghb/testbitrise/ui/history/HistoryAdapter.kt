package com.duonghb.testbitrise.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duonghb.testbitrise.databinding.ItemHistoryBinding
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData

class HistoryAdapter(
    private val clickHistoryItemCallback: ((historyModelData: HistoryModelData) -> Unit)
) : RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {

    private val listNewsHistory = mutableListOf<HistoryModelData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val binding = ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return HistoryViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val news: HistoryModelData = listNewsHistory.get(position)
        holder.bind(news)
    }

    override fun getItemCount(): Int {
        return listNewsHistory.size
    }

    fun setHistoryItems(items: List<HistoryModelData>) {
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(HistoryDiffUtilCallBack(items, listNewsHistory))

        diffResult.dispatchUpdatesTo(this)
        listNewsHistory.clear()
        listNewsHistory.addAll(items)
    }

    inner class HistoryViewHolder(itemView: ItemHistoryBinding, private val context: Context) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemHistoryBinding = itemView

        fun bind(news: HistoryModelData) {
            binding.modelHistory = news

            binding.root.setOnClickListener {
                clickHistoryItemCallback.invoke(news)
            }

            Glide.with(context).load(news.imageUrl).into(binding.newsHistoryImageView)
        }
    }
}
