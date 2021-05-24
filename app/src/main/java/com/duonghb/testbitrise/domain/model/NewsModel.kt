package com.duonghb.testbitrise.domain.model

data class NewsModel(
    val section: String,
    val results: List<NewsModelData>
)

data class NewsModelData(
    val title: String,
    val section: String,
    val abstract: String,
    val url: String,
    val multimedia: List<NewsImage>
)

data class NewsImage(
    val url: String
)
