package com.vitalik123.database.repository.watchlist

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.database.entity.WatchlistEntity

interface WatchlistRoomRepository {

    suspend fun getAllWatchlist(): List<WatchlistEntity>

    suspend fun deleteAll()

    suspend fun saveMovieToWatchlist(film: FilmDetailsUi?)

    suspend fun deleteMovieFromWatchlist(kinopoiskId: Long)

    suspend fun getMovieFromWatchlist(kinopoiskId: Long): FilmDetailsUi

    suspend fun existsMovieToWatchlist(kinopoiskId: Long): Boolean
}