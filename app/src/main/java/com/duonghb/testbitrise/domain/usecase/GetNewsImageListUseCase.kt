package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.data.repository.NewsRepositoryImpl
import com.duonghb.testbitrise.domain.model.NewsModelData
import io.reactivex.Single
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetNewsImageListUseCase @Inject constructor(
    private val newsRepositoryImpl: NewsRepositoryImpl
) {
    operator fun invoke(): Single<NewsModelData> {
        return newsRepositoryImpl.getNewsImageList()
    }
}
