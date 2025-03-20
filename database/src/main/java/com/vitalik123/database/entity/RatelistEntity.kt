package com.vitalik123.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.vitalik123.core.utils.Constants

@Entity(tableName = Constants.TABLE_NAME_RATELIST_ENTITY)
data class RatelistEntity(
    @ColumnInfo(name = "id") @PrimaryKey(autoGenerate = true) val databaseId: Int,
    @ColumnInfo(name = "kinopoisk_id") val kinopoiskId: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "poster") val poster: String,
    @ColumnInfo(name = "rating") val rating: Double?,
    @ColumnInfo(name = "my_rating") val myRating: Int
)
