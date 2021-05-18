package com.duonghb.testbitrise.network

import com.duonghb.testbitrise.domain.model.NewsModel
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*

const val GET_NEWS_LIST: String = "/home.json?api-key=t4EG49MADcsJR9zFouDYO5ANI1rpJTAf"
const val BASE_URL: String = "/v2/home.json?api-key=t4EG49MADcsJR9zFouDYO5ANI1rpJTAf"

interface ApiService {
    @FormUrlEncoded
    fun getAccessToken(
        @Field("client_image") clientImage: Int,
        @Field("client_section") clientSection: String,
        @Field("client_title") clientTitle: String,
        @Field("client_description") clientDescription: String
    ): Single<NewsModel>

    @GET(GET_NEWS_LIST)
    fun getNews(): Single<List<NewsModel>>

    companion object {
        fun create(): ApiService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build().create(ApiService::class.java)
            return retrofit
        }
    }
}
