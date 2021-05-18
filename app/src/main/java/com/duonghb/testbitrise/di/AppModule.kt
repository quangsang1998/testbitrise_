package com.duonghb.testbitrise.di

import com.duonghb.testbitrise.domain.usecase.GetNewsListUseCase
import com.duonghb.testbitrise.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    @Provides
    @Singleton
    fun provideViewModel(getNewsListUseCase: GetNewsListUseCase): HomeViewModel {
        return HomeViewModel(getNewsListUseCase)
    }
}
