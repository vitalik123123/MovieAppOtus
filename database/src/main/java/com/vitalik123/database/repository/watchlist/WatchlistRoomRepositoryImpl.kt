package com.vitalik123.database.repository.watchlist

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.database.dao.WatchlistDao
import com.vitalik123.database.entity.WatchlistEntity
import com.vitalik123.database.mapper.toFilmDetailsUi
import com.vitalik123.database.mapper.toWatchlistEntity

class WatchlistRoomRepositoryImpl(
    private val watchlistDao: WatchlistDao
) : WatchlistRoomRepository {

    override suspend fun getAllWatchlist(): List<WatchlistEntity> =
        watchlistDao.getAllWatchlist()

    override suspend fun deleteAll() =
        watchlistDao.deleteAll()

    override suspend fun saveMovieToWatchlist(film: FilmDetailsUi?) {
        if (film != null)
            watchlistDao.saveMovieToWatchlist(film.toWatchlistEntity())
    }

    override suspend fun deleteMovieFromWatchlist(kinopoiskId: Long) =
        watchlistDao.deleteMovieFromWatchlist(kinopoiskId)

    override suspend fun getMovieFromWatchlist(kinopoiskId: Long): FilmDetailsUi =
        watchlistDao.getMovieFromWatchlist(kinopoiskId).toFilmDetailsUi()

    override suspend fun existsMovieToWatchlist(kinopoiskId: Long): Boolean =
        watchlistDao.existsMovieToWatchlist(kinopoiskId)
}