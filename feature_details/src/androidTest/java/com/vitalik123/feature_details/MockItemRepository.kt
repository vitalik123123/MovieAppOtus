package com.vitalik123.feature_details

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.database.entity.WatchlistEntity
import com.vitalik123.database.mapper.toFilmDetailsUi
import com.vitalik123.database.mapper.toWatchlistEntity
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepository

class MockItemRepository : WatchlistRoomRepository {

    private val items = mutableListOf<WatchlistEntity>()

    override suspend fun getAllWatchlist(): List<WatchlistEntity> = items.toList()

    override suspend fun deleteAll() {}

    override suspend fun saveMovieToWatchlist(film: FilmDetailsUi?) {
        if (film != null)
        items.add(film.toWatchlistEntity())
    }

    override suspend fun deleteMovieFromWatchlist(kinopoiskId: Long) {
        val item = items.find { it.kinopoiskId == kinopoiskId }
        items.remove(item)
    }

    override suspend fun getMovieFromWatchlist(kinopoiskId: Long): FilmDetailsUi {
        return items.find { it.kinopoiskId == kinopoiskId }?.toFilmDetailsUi() ?: mockItem1
    }

    override suspend fun existsMovieToWatchlist(kinopoiskId: Long): Boolean {
        return items.any { it.kinopoiskId == kinopoiskId }
    }
}

val mockItem1 = FilmDetailsUi(
    id = 1,
    title = "title",
    rating = 8.6,
    poster = "",
    year = "2000",
    description = "",
    titleAndYear = ""
)
val mockItem2 = FilmDetailsUi(
    id = 2,
    title = "title",
    rating = 8.6,
    poster = "",
    year = "2000",
    description = "",
    titleAndYear = ""
)
val mockItem3 = FilmDetailsUi(
    id = 3,
    title = "title",
    rating = 8.6,
    poster = "",
    year = "2000",
    description = "",
    titleAndYear = ""
)
val mockItem4 = FilmDetailsUi(
    id = 4,
    title = "title",
    rating = 8.6,
    poster = "",
    year = "2000",
    description = "",
    titleAndYear = ""
)
val mockItem5 = FilmDetailsUi(
    id = 5,
    title = "title",
    rating = 8.6,
    poster = "",
    year = "2000",
    description = "",
    titleAndYear = ""
)