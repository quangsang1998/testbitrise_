package com.duonghb.testbitrise.network

import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.util.constant.Constant
import io.reactivex.Single
import retrofit2.http.GET

interface ApiService {

    @GET(Constant.GET_NEWS_LIST)
    fun getNews(): Single<NewsModel>
}
