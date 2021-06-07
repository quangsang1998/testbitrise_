package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.NewsRepositoryImpl
import io.reactivex.Completable
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DeleteNewsHistoryUseCase @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) {
    operator fun invoke(url: String): Completable {
        return newsRepositoryImpl.deleteNewsHistory(url)
    }
}
