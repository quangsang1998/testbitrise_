package com.duonghb.testbitrise.data.repository

import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.repository.NewsRepository
import com.duonghb.testbitrise.network.ApiService
import com.duonghb.testbitrise.util.SchedulerProvider
import io.reactivex.Single

class NewsRepositoryImpl(
    private val schedulerProvider: SchedulerProvider
) : NewsRepository {

    override fun getNewsList(): Single<List<NewsModel>> {
        return ApiService.create().getNews()
            .observeOn(schedulerProvider.io())
            .subscribeOn(schedulerProvider.io())
    }
}
