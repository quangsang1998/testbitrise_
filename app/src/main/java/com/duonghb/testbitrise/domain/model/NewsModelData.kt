package com.duonghb.testbitrise.domain.model

enum class NewsCompareValue(val value: Int) {
    EQUAL(0),
    NOT_EQUAL(1),
}

data class NewsModelData(
    var id: Int?,
    var time: Long?,
    val title: String,
    val section: String,
    val abstract: String,
    val url: String,
    val multimedia: List<NewsImage>
) : Comparable<NewsModelData> {

    override fun compareTo(other: NewsModelData): Int {
        val compare: NewsModelData = other

        if (compare.url.equals(this.url)) {
            return NewsCompareValue.EQUAL.value
        } else {
            return NewsCompareValue.NOT_EQUAL.value
        }
    }
}
