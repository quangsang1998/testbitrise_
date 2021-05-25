package com.duonghb.testbitrise.data.repository

import com.duonghb.testbitrise.data.database.dao.HistoryDao
import com.duonghb.testbitrise.data.database.entity.History
import com.duonghb.testbitrise.data.mapper.toModel
import com.duonghb.testbitrise.domain.historymodel.HistoryModelData
import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.model.NewsModelData
import com.duonghb.testbitrise.network.ApiService
import io.reactivex.Completable
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepositoryImpl @Inject constructor(
    private val apiService: ApiService,
    private val historyDao: HistoryDao,
) {
    fun getNewsList(): Single<NewsModel> {
        return apiService.getNews()
    }

    fun getNewsHistoryDatabase(): Single<List<HistoryModelData>> {
        return historyDao.getAll()
            .map { list -> list.map { it.toModel() } }
    }

    fun saveNewsHistoryDatabase(model: NewsModelData): Completable {
        val entity = History.fromModel(model)
        return historyDao.insert(entity)
    }
}
