package com.duonghb.testbitrise.network

import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.domain.model.NewsModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constant.GET_NEWS_LIST)
    fun getNews(@Query("api-key") apiKey: String): Single<NewsModel>
}
