package com.duonghb.testbitrise.di

import com.duonghb.testbitrise.util.AppScheduleProvider
import com.duonghb.testbitrise.util.SchedulerProvider
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class SchedulerModule {

    @Binds
    abstract fun bindScheduler(appScheduleProvider: AppScheduleProvider): SchedulerProvider
}
