package com.duonghb.testbitrise.data.repository

import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.network.ApiService
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) {
    fun getNewsList(): Single<List<NewsModel>> {
        return apiService.getNews()
    }
}
