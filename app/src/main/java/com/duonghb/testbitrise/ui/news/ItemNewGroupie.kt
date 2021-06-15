package com.duonghb.testbitrise.ui.news

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.ItemNewsBinding
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.xwray.groupie.viewbinding.BindableItem

class ItemNewGroupie(private val clickItemCallback: ((newsModelData: NewsModelData) -> Unit)) :
    BindableItem<ItemNewsBinding>() {

    private val listNews = mutableListOf<NewsModelData>()

    override fun bind(itemView: ItemNewsBinding, position: Int) {
        val binding: ItemNewsBinding = itemView
        val news: NewsModelData = listNews.get(position)

        binding.model = news

        binding.root.setOnClickListener {
            clickItemCallback.invoke(news)
        }
    }

    override fun initializeViewBinding(view: View): ItemNewsBinding {
        return ItemNewsBinding.bind(view)
    }

    override fun getLayout(): Int = R.layout.item_news

    override fun getItemCount(): Int {
        return listNews.size
    }
}
