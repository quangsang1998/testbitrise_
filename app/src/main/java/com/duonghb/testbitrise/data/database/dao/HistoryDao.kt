package com.duonghb.testbitrise.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.duonghb.testbitrise.data.database.entity.History
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface HistoryDao {
    @Query(" SELECT * FROM ${History.NAME} ORDER BY time DESC")
    fun getAll(): Single<List<History>>

    @Insert
    fun insert(entity: History): Completable
}
