package com.duonghb.testbitrise.di

import android.content.Context
import androidx.room.Room
import com.duonghb.testbitrise.constant.Constant
import com.duonghb.testbitrise.data.database.ToDoDatabase
import com.duonghb.testbitrise.data.database.dao.HistoryDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    fun bindNewsHistoryDao(toDoDatabase: ToDoDatabase): HistoryDao {
        return toDoDatabase.historyDao()
    }

    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ToDoDatabase {
        return Room.databaseBuilder(context, ToDoDatabase::class.java, Constant.DB_NAME).build()
    }
}
