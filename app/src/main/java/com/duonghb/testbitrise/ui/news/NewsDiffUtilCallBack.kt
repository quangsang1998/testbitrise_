package com.duonghb.testbitrise.ui.news

import android.os.Bundle
import androidx.annotation.Nullable
import androidx.recyclerview.widget.DiffUtil
import com.duonghb.testbitrise.domain.model.NewsModelData

class NewsDiffUtilCallBack(
    private val newList: List<NewsModelData>,
    private val oldList: List<NewsModelData>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return newList.get(newItemPosition).title == oldList.get(oldItemPosition).title
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val result = newList.get(newItemPosition).compareTo(oldList.get(oldItemPosition))
        return result == 0
    }

    @Nullable
    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val newModel = newList.get(newItemPosition)
        val oldModel = oldList.get(oldItemPosition)

        val diff: Bundle = Bundle()

        if (newModel.title != oldModel.title) {
            diff.putString("title", newModel.title)
        }
        if (diff.size() == 0) {
            return null
        } else {
            return diff
        }
    }
}
