package com.duonghb.testbitrise.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.duonghb.testbitrise.data.database.dao.HistoryDao
import com.duonghb.testbitrise.data.database.entity.History
import javax.inject.Singleton

@Database(
    entities = [
        History::class
    ],
    version = 1
)

abstract class ToDoDatabase : RoomDatabase() {
    abstract fun historyDao(): HistoryDao
}
