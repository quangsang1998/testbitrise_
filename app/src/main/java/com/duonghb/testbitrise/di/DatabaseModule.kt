package com.duonghb.testbitrise.di

import com.duonghb.testbitrise.data.database.ToDoDatabase
import com.duonghb.testbitrise.data.database.dao.HistoryDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun bindNewsHistoryDao(toDoDatabase: ToDoDatabase): HistoryDao {
        return toDoDatabase.historyDao()
    }
}
