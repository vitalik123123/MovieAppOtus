package com.vitalik123.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.vitalik123.database.dao.RatelistDao
import com.vitalik123.database.dao.WatchlistDao
import com.vitalik123.database.entity.RatelistEntity
import com.vitalik123.database.entity.WatchlistEntity

@Database(
    entities = [WatchlistEntity::class, RatelistEntity::class],
    version = 2
)
abstract class MovieRoomDatabase: RoomDatabase() {

    abstract fun watchlistDao(): WatchlistDao

    abstract fun ratelistDao(): RatelistDao
}