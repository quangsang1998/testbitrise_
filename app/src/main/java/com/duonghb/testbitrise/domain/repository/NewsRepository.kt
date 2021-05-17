package com.duonghb.testbitrise.domain.repository

import com.duonghb.testbitrise.domain.model.NewsModel
import io.reactivex.Single

interface NewsRepository {
    fun getNewsList(): Single<List<NewsModel>>
}
