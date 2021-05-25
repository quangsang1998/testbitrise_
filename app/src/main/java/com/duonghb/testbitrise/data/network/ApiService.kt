package com.duonghb.testbitrise.network

import com.duonghb.testbitrise.domain.model.NewsModel
import com.duonghb.testbitrise.domain.model.NewsModelData
import io.reactivex.Single
import retrofit2.http.GET

class SupplyConstant {
    companion object {
        const val GET_NEWS_LIST: String = "home.json?api-key=t4EG49MADcsJR9zFouDYO5ANI1rpJTAf"
    }
}

interface ApiService {

    @GET(SupplyConstant.GET_NEWS_LIST)
    fun getNews(): Single<NewsModel>
}
