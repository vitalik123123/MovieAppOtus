package com.vitalik123.database.repository.ratelist

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.database.entity.RatelistEntity

interface RatelistRoomRepository {

    suspend fun getAllLocalRatinglist(): List<RatelistEntity>

    suspend fun deleteAll()

    suspend fun saveMyRatingToRatinglist(film: FilmDetailsUi, myRating: Int)

    suspend fun getMyRatingFromRatinglistToDetails(kinopoiskId: Long): RatelistEntity

    suspend fun deleteMyRatingFromRatinglist(kinopoiskId: Long)

    suspend fun updateMyRatingFromRatinglist(kinopoiskId: Long, myRating: Int)

    suspend fun existsMyRatingToRatinglist(kinopoiskId: Long): Boolean
}