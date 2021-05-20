package com.duonghb.testbitrise.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.duonghb.testbitrise.databinding.ItemNewsBinding
import com.duonghb.testbitrise.domain.model.NewsModel

class NewsAdapter(
    private val clickItemCallback: (() -> Unit)
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val listNews = mutableListOf<NewsModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int {
        return 10
    }

    fun setItems(items: List<NewsModel>) {
        listNews.clear()
        listNews.addAll(items)
        notifyDataSetChanged()
    }

    inner class NewsViewHolder(itemView: ItemNewsBinding) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemNewsBinding = itemView

        fun bind() {
            binding.root.setOnClickListener {
                clickItemCallback.invoke()
            }
        }
    }
}
