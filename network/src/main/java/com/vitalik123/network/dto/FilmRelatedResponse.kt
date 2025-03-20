package com.vitalik123.network.dto

import com.vitalik123.core.dto.response.RelationType

data class FilmRelatedResponse(
    val filmId: Long,
    val nameRu: String?,
    val nameEn: String?,
    val nameOriginal: String?,
    val posterUrl: String?,
    val posterUrlPreview: String?,
    val relationType: RelationType
)
