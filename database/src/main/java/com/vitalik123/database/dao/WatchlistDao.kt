package com.vitalik123.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitalik123.core.utils.Constants
import com.vitalik123.database.entity.WatchlistEntity

@Dao
interface WatchlistDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME_WATCHLIST_ENTITY}")
    suspend fun getAllWatchlist(): List<WatchlistEntity>

    @Query("DELETE FROM ${Constants.TABLE_NAME_WATCHLIST_ENTITY}")
    suspend fun deleteAll()

    @Insert(entity = WatchlistEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveMovieToWatchlist(film: WatchlistEntity)

    @Query("DELETE FROM ${Constants.TABLE_NAME_WATCHLIST_ENTITY} WHERE kinopoisk_id = :kinopoiskId")
    suspend fun deleteMovieFromWatchlist(kinopoiskId: Long)

    @Query("SELECT * FROM ${Constants.TABLE_NAME_WATCHLIST_ENTITY} WHERE kinopoisk_id = :kinopoiskId LIMIT 1")
    suspend fun getMovieFromWatchlist(kinopoiskId: Long): WatchlistEntity

    @Query("SELECT EXISTS(SELECT * FROM ${Constants.TABLE_NAME_WATCHLIST_ENTITY} WHERE kinopoisk_id =:kinopoiskId)")
    suspend fun existsMovieToWatchlist(kinopoiskId: Long): Boolean
}