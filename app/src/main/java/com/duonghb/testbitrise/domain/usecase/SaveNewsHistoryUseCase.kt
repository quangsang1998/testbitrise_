package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.NewsRepositoryImpl
import com.duonghb.testbitrise.domain.model.NewsModelData
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SaveNewsHistoryUseCase @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) {
    operator fun invoke(model: NewsModelData): Completable {
        return newsRepositoryImpl.saveHistory(model)
    }
}
