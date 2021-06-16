package com.duonghb.testbitrise.di

import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.network.ApiService
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module()
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideApiService(retrofit: Retrofit): ApiService = retrofit.create(ApiService::class.java)

    @Provides
    fun provideRetrofit(gson: Gson, httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
        .baseUrl(Constant.BASE_URL)
        .client(httpClient)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()

    @Provides
    fun provideClientBuilder(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()

        return clientBuilder.addInterceptor(httpLoggingInterceptor).build()
    }

    @Provides
    fun provideGson(): Gson {
        return GsonBuilder().setLenient().create()
    }

    @Provides
    fun provideOkHttp(): HttpLoggingInterceptor {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return loggingInterceptor
    }

    @Provides
    fun provideGroupieAdapter() = GroupAdapter<GroupieViewHolder>()
}
