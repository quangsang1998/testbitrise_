package com.duonghb.testbitrise.domain.model

data class NewsModelData(
    var time: Long?,
    val title: String,
    val section: String,
    val abstract: String,
    val url: String,
    val multimedia: List<NewsImage>
) : Comparable<NewsModelData>, Cloneable {

    override fun compareTo(other: NewsModelData): Int {
        val compare: NewsModelData = other

        if (compare.url.equals(this.url)) {
            return 0
        } else {
            return 1
        }
    }

    override fun clone(): NewsModelData {
        val clone: NewsModelData
        try {
            clone = super.clone() as NewsModelData
        } catch (e: CloneNotSupportedException) {
            throw RuntimeException(e)
        }
        return clone
    }
}
