package com.duonghb.testbitrise.domain.usecase

import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.repository.NewsRepository
import io.reactivex.Single

class GetNewsListUseCase(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(): Single<List<NewsModel>> {
        return newsRepository.getNewsList()
    }
}
