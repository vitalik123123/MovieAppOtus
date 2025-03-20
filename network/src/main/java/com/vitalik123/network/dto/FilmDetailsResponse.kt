package com.vitalik123.network.dto

import com.vitalik123.core.dto.response.Country
import com.vitalik123.core.dto.response.FilmType
import com.vitalik123.core.dto.response.Genre
import com.vitalik123.core.dto.response.ProductionStatus

data class FilmDetailsResponse(
    val kinopoiskId: Long,
    val kinopoiskHDId: String?,
    val imdbId: String?,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val posterUrl: String,
    val posterUrlPreview: String,
    val coverUrl: String?,
    val logoUrl: String?,
    val reviewsCount: Int,
    val ratingGoodReview: Double?,
    val ratingGoodReviewVoteCount: Int?,
    val ratingKinopoisk: Double?,
    val ratingKinopoiskVoteCount: Int?,
    val ratingImdb: Double?,
    val ratingImdbVoteCount: Int?,
    val ratingFilmCritics: Double?,
    val ratingFilmCriticsVoteCount: Int?,
    val ratingAwait: Double?,
    val ratingAwaitCount: Int?,
    val ratingRfCritics: Double?,
    val ratingRfCriticsVoteCount: Int?,
    val webUrl: String,
    val year: Int?,
    val filmLength: Int?,
    val slogan: String?,
    val description: String?,
    val shortDescription: String?,
    val editorAnnotation: String?,
    val isTicketsAvailable: Boolean,
    val productionStatus: ProductionStatus?,
    val type: FilmType,
    val ratingMpaa: String?,
    val ratingAgeLimits: String?,
    val hasImax: Boolean?,
    val has3D: Boolean?,
    val lastSync: String,
    val countries: List<Country>?,
    val genres: List<Genre>?,
    val startYear: Int?,
    val endYear: Int?,
    val serial: Boolean?,
    val shortFilm: Boolean?,
    val completed: Boolean?
)
