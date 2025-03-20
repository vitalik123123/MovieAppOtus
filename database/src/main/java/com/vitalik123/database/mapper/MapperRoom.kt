package com.vitalik123.database.mapper

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.database.entity.RatelistEntity
import com.vitalik123.database.entity.WatchlistEntity

fun FilmDetailsUi.toWatchlistEntity() : WatchlistEntity = WatchlistEntity(
    databaseId = 0,
    kinopoiskId = this.id,
    title = this.title,
    poster = this.poster.orEmpty(),
    rating = this.rating
)

fun WatchlistEntity.toFilmDetailsUi(): FilmDetailsUi = FilmDetailsUi(
    id = this.kinopoiskId,
    title = this.title,
    rating = this.rating,
    poster = this.poster,
    year = "",
    description = "",
    titleAndYear = this.title
)

fun FilmDetailsUi.toRatelistEntity(myRating: Int) : RatelistEntity = RatelistEntity(
    databaseId = 0,
    kinopoiskId = this.id,
    title = this.title,
    poster = this.poster.orEmpty(),
    rating = this.rating,
    myRating = myRating
)

fun RatelistEntity.toFilmDetailsUi(): FilmDetailsUi = FilmDetailsUi(
    id = this.kinopoiskId,
    title = this.title,
    rating = this.rating,
    poster = this.poster,
    year = "",
    description = "",
    titleAndYear = this.title
)