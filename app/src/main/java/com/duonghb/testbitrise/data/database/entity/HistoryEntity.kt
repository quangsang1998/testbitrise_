package com.duonghb.testbitrise.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.duonghb.testbitrise.domain.model.NewsModelData
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
@Entity(tableName = History.NAME)
data class History @Inject constructor(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = COLUMN_ID) var id: Int?,
    @ColumnInfo(name = COLUMN_TIME) var time: Long?,
    @ColumnInfo(name = COLUMN_TITLE) val title: String,
    @ColumnInfo(name = COLUMN_SECTION) val section: String,
    @ColumnInfo(name = COLUMN_URL) val url: String,
    @ColumnInfo(name = COLUMN_IMAGE_URL) val imageUrl: String
) {
    companion object {
        const val NAME = "news_model_data"
        const val COLUMN_ID = "id"
        const val COLUMN_TIME = "time"
        const val COLUMN_TITLE = "title"
        const val COLUMN_SECTION = "section"
        const val COLUMN_URL = "url"
        const val COLUMN_IMAGE_URL = "image_url"

        fun fromModel(model: NewsModelData) = History(
            id = model.id,
            time = model.time,
            title = model.title,
            section = model.section,
            url = model.url,
            imageUrl = model.multimedia.first().url
        )
    }
}
