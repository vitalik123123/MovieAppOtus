package com.vitalik123.network.dto

import com.vitalik123.core.dto.response.Country
import com.vitalik123.core.dto.response.FilmType
import com.vitalik123.core.dto.response.Genre

data class FilmSearchResponse(
    val keyword: String,
    val pagesCount: Int,
    val searchFilmsCountResult: Int,
    val films: List<FilmSearchResponseItem>
)

data class FilmSearchResponseItem(
    val filmId: Long,
    val nameRu: String?,
    val nameEn: String?,
    val type: FilmType,
    val year: String?,
    val description: String?,
    val filmLength: String?,
    val countries: List<Country>,
    val genres: List<Genre>,
    val rating: String?,
    val ratingVoteCount: Int?,
    val posterUrl: String?,
    val posterUrlPreview: String?
)
