package com.duonghb.testbitrise.data.database.dao

import androidx.room.*
import com.duonghb.testbitrise.data.database.entity.History
import io.reactivex.Completable
import io.reactivex.Single
import org.jetbrains.annotations.NotNull

@Dao
interface HistoryDao {

    @Query("DELETE FROM ${History.NAME} WHERE ${History.COLUMN_URL} = :url")
    fun delete(url: String): Completable

    @Query(" SELECT * FROM ${History.NAME} ORDER BY time DESC")
    fun getAll(): Single<List<History>>

    @Insert
    fun insert(entity: History): Completable
}
