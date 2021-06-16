package com.duonghb.testbitrise.ui.history

import android.view.View
import com.duonghb.testbitrise.R
import com.duonghb.testbitrise.databinding.ItemHistoryBinding
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData
import com.xwray.groupie.Item
import com.xwray.groupie.viewbinding.BindableItem

data class HistoryListItemViewModel(
    val historyItem: HistoryModelData,
    val listener: Listener
) {

    interface Listener {
        fun onItemHistoryClick(historyItem: HistoryModelData)
    }

    fun onItemHistoryClick() {
        listener.onItemHistoryClick(historyItem)
    }

    val url get() = historyItem.imageUrl
}

class HistoryListItem(
    private val viewModel: HistoryListItemViewModel
) : BindableItem<ItemHistoryBinding>() {

    override fun getLayout(): Int = R.layout.item_history

    override fun bind(viewBinding: ItemHistoryBinding, position: Int) {
        val binding: ItemHistoryBinding = viewBinding
        binding.viewModelHistory = viewModel
        viewBinding.executePendingBindings()
    }

    override fun initializeViewBinding(view: View): ItemHistoryBinding {
        return ItemHistoryBinding.bind(view)
    }

    override fun isSameAs(other: Item<*>): Boolean =
        (other as HistoryListItem).viewModel.historyItem.url ==
            viewModel.historyItem.url

    override fun hasSameContentAs(other: Item<*>): Boolean =
        (other as HistoryListItem).viewModel.historyItem == viewModel.historyItem
}
