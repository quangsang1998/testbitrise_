package com.duonghb.testbitrise.domain.model

data class NewsModelData(
    val title: String,
    val section: String,
    val abstract: String,
    val url: String,
    val multimedia: List<NewsImage>
)
