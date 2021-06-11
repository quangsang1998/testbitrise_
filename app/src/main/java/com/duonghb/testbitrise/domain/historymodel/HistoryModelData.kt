package com.duonghb.testbitrise.domain.historymodel

enum class HistoryCompareValue(val value: Int) {
    EQUAL(0),
    NOT_EQUAL(1),
}

data class HistoryModelData(
    val id: Int?,
    val time: Long?,
    val title: String,
    val section: String,
    val url: String,
    val imageUrl: String
) : Comparable<HistoryModelData> {

    override fun compareTo(other: HistoryModelData): Int {
        val compare: HistoryModelData = other

        if (compare.url.equals(this.url)) {
            return HistoryCompareValue.EQUAL.value
        } else {
            return HistoryCompareValue.NOT_EQUAL.value
        }
    }
}
