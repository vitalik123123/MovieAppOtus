package com.vitalik123.network.mapper

import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.network.dto.FilmCollectionResponseItem
import com.vitalik123.network.dto.FilmSearchResponseItem

fun FilmCollectionResponseItem.toFilmUi(): FilmUi = FilmUi(
    id = this.kinopoiskId ?: -1,
    title = this.nameRu ?: this.nameEn ?: this.nameOriginal.orEmpty(),
    rating = this.ratingKinopoisk,
    poster = this.posterUrl
)

fun FilmSearchResponseItem.toFilmUi(): FilmUi = FilmUi(
    id = this.filmId,
    title = this.nameRu ?: this.nameEn.orEmpty(),
    rating = try {
        this.rating?.toDouble()
    } catch (e: Exception) {
        null
    },
    poster = this.posterUrl
)

