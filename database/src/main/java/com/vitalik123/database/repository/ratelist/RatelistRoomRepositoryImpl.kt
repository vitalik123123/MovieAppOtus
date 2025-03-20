package com.vitalik123.database.repository.ratelist

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.database.dao.RatelistDao
import com.vitalik123.database.entity.RatelistEntity
import com.vitalik123.database.mapper.toRatelistEntity

class RatelistRoomRepositoryImpl(
    private val ratelistDao: RatelistDao
) : RatelistRoomRepository {

    override suspend fun getAllLocalRatinglist(): List<RatelistEntity> =
        ratelistDao.getAllRatelist()

    override suspend fun deleteAll() = ratelistDao.deleteAll()

    override suspend fun saveMyRatingToRatinglist(film: FilmDetailsUi, myRating: Int) =
        ratelistDao.saveRatingToRatelist(
            film = film.toRatelistEntity(myRating)
        )

    override suspend fun getMyRatingFromRatinglistToDetails(kinopoiskId: Long): RatelistEntity =
        ratelistDao.getMyRatingFromRatinglistToDetails(kinopoiskId = kinopoiskId)

    override suspend fun deleteMyRatingFromRatinglist(kinopoiskId: Long) =
        ratelistDao.deleteMyRatingFromRatinglist(kinopoiskId = kinopoiskId)

    override suspend fun updateMyRatingFromRatinglist(kinopoiskId: Long, myRating: Int) =
        ratelistDao.updateMyRatingFromRatinglist(kinopoiskId = kinopoiskId, myRating = myRating)

    override suspend fun existsMyRatingToRatinglist(kinopoiskId: Long): Boolean =
        ratelistDao.existsMyRatingToRatinglist(kinopoiskId = kinopoiskId)
}