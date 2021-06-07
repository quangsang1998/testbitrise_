package com.duonghb.testbitrise.domain.historymodel

data class HistoryModelData(
    val time: Long?,
    val title: String,
    val section: String,
    val url: String,
    val imageUrl: String
) : Comparable<HistoryModelData>, Cloneable {

    override fun compareTo(other: HistoryModelData): Int {
        val compare: HistoryModelData = other

        if (compare.url.equals(this.url)) {
            return 0
        } else {
            return 1
        }
    }

    override fun clone(): HistoryModelData {
        val clone: HistoryModelData
        try {
            clone = super.clone() as HistoryModelData
        } catch (e: CloneNotSupportedException) {
            throw RuntimeException(e)
        }
        return clone
    }
}
