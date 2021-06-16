package com.duonghb.testbitrise.ui.news

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.ItemNewsBinding
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class NewListItemViewModel(
    val newItem: NewsModelData,
    val listener: Listener
) {

    interface Listener {
        fun onItemClick(newItem: NewsModelData)
    }

    fun onItemClick() {
        listener.onItemClick(newItem)
    }

    val url get() = newItem.multimedia.get(0).url
}

class NewListItem(
    private val viewModel: NewListItemViewModel
) : BindableItem<ItemNewsBinding>() {

    override fun getLayout(): Int = R.layout.item_news

    override fun bind(viewBinding: ItemNewsBinding, position: Int) {
        val binding: ItemNewsBinding = viewBinding
        binding.viewModel = viewModel
        viewBinding.executePendingBindings()
    }

    override fun initializeViewBinding(view: View): ItemNewsBinding {
        return ItemNewsBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as NewListItem).viewModel.newItem.url ==
            viewModel.newItem.url

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as NewListItem).viewModel.newItem == viewModel.newItem
}
