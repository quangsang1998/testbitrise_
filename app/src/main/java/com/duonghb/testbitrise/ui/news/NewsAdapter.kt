package com.duonghb.testbitrise.ui.news

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.duonghb.testbitrise.databinding.ItemNewsBinding
import com.duonghb.testbitrise.domain.model.NewsImage
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.model.NewsModelData

class NewsAdapter(
    private val clickItemCallback: ((newsModelData: NewsModelData) -> Unit)
) : RecyclerView.Adapter<NewsAdapter.NewsViewHolder>() {

    private val listNews = mutableListOf<NewsModelData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val news: NewsModelData = listNews.get(position)

        holder.bind(news, news.multimedia.first())
    }

    override fun getItemCount(): Int {
        return listNews.size
    }

    fun setItems(items: NewsModel) {
        val diffResult: DiffUtil.DiffResult = DiffUtil.calculateDiff(NewsDiffUtilCallBack(items.results, listNews))

        diffResult.dispatchUpdatesTo(this)
        listNews.clear()
        listNews.addAll(items.results)
    }

    inner class NewsViewHolder(itemView: ItemNewsBinding, private val context: Context) : RecyclerView.ViewHolder(itemView.root) {
        var binding: ItemNewsBinding = itemView

        fun bind(news: NewsModelData, newsImage: NewsImage) {
            binding.model = news
            binding.modelImage = newsImage

            binding.root.setOnClickListener {
                clickItemCallback.invoke(news)
            }

            Glide.with(context).load(newsImage.url).into(binding.newsImageView)
        }
    }
}
