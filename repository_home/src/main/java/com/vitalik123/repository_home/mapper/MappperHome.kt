package com.vitalik123.repository_home.mapper

import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.network.dto.FilmCollectionResponseItem

fun FilmCollectionResponseItem.toFilmUi(): FilmUi = FilmUi(
    id = this.kinopoiskId ?: -1,
    title = this.nameRu,
    rating = this.ratingKinopoisk,
    poster = this.posterUrl
)