package com.vitalik123.network.dto

import com.vitalik123.core.dto.response.Country
import com.vitalik123.core.dto.response.FilmType
import com.vitalik123.core.dto.response.Genre

data class FilmCollectionResponse(
    val total: Int,
    val totalPages: Int,
    val items: List<FilmCollectionResponseItem>
)

data class FilmCollectionResponseItem(
    val kinopoiskId: Long?,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val countries: List<Country>?,
    val genres: List<Genre>?,
    val ratingKinopoisk: Double?,
    val ratingImbd: Double?,
    val year: String?,
    val type: FilmType?,
    val posterUrl: String?,
    val posterUrlPreview: String?
)
