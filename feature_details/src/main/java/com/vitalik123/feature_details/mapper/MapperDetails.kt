package com.vitalik123.feature_details.mapper

import com.vitalik123.core.dto.response.ProfessionKey
import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.feature_details.dto.StaffUi
import com.vitalik123.network.dto.FilmCastResponse
import com.vitalik123.network.dto.FilmDetailsResponse
import com.vitalik123.network.dto.FilmRelatedResponse

fun FilmDetailsResponse.toFilmDetailsUi(): FilmDetailsUi = FilmDetailsUi(
    id = this.kinopoiskId,
    title = this.nameRu ?: this.nameEn ?: this.nameOriginal.orEmpty(),
    rating = this.ratingKinopoisk,
    poster = this.posterUrl,
    year = this.year?.toString().orEmpty(),
    description = this.description.orEmpty(),
    titleAndYear = "${this.nameRu ?: this.nameEn ?: this.nameOriginal.orEmpty()} (${this.year})"
)

fun FilmCastResponse.toStaffUi(): StaffUi = StaffUi(
    poster = this.posterUrl,
    name = this.nameRu?.replace(" ", "\n") ?: this.nameEn?.replace(" ", "\n").orEmpty(),
    role = when(this.professionKey) {
        ProfessionKey.WRITER -> this.professionText
        ProfessionKey.OPERATOR -> this.professionText
        ProfessionKey.EDITOR -> this.professionText
        ProfessionKey.COMPOSER -> this.professionText
        ProfessionKey.PRODUCER_USSR -> this.professionText
        ProfessionKey.TRANSLATOR -> this.professionText
        ProfessionKey.DIRECTOR -> "Режиссер"
        ProfessionKey.DESIGN -> this.professionText
        ProfessionKey.PRODUCER -> this.professionText
        ProfessionKey.ACTOR -> "Актер"
        ProfessionKey.VOICE_DIRECTOR -> this.professionText
        ProfessionKey.UNKNOWN -> this.professionText
    }
)

fun FilmRelatedResponse.toFilmUi(): FilmUi = FilmUi(
    id = this.filmId,
    title = this.nameRu ?: this.nameEn ?: this.nameOriginal.orEmpty(),
    rating = null,
    poster = this.posterUrl
)