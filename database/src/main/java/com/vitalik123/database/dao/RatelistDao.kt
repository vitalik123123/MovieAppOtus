package com.vitalik123.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.vitalik123.core.utils.Constants
import com.vitalik123.database.entity.RatelistEntity

@Dao
interface RatelistDao {

    @Query("SELECT * FROM ${Constants.TABLE_NAME_RATELIST_ENTITY}")
    suspend fun getAllRatelist(): List<RatelistEntity>

    @Query("DELETE FROM ${Constants.TABLE_NAME_RATELIST_ENTITY}")
    suspend fun deleteAll()

    @Insert(entity = RatelistEntity::class, onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveRatingToRatelist(film: RatelistEntity)

    @Query("SELECT * FROM ${Constants.TABLE_NAME_RATELIST_ENTITY} WHERE kinopoisk_id = :kinopoiskId LIMIT 1")
    suspend fun getMyRatingFromRatinglistToDetails(kinopoiskId: Long): RatelistEntity

    @Query("DELETE FROM ${Constants.TABLE_NAME_RATELIST_ENTITY} WHERE kinopoisk_id = :kinopoiskId")
    suspend fun deleteMyRatingFromRatinglist(kinopoiskId: Long)

    @Query("UPDATE ${Constants.TABLE_NAME_RATELIST_ENTITY} SET my_rating = :myRating WHERE kinopoisk_id = :kinopoiskId")
    suspend fun updateMyRatingFromRatinglist(kinopoiskId: Long, myRating: Int)

    @Query("SELECT EXISTS(SELECT * FROM ${Constants.TABLE_NAME_RATELIST_ENTITY} WHERE kinopoisk_id = :kinopoiskId)")
    suspend fun existsMyRatingToRatinglist(kinopoiskId: Long): Boolean
}