package com.vitalik123.network.dto

import com.vitalik123.core.dto.response.ProfessionKey

data class FilmCastResponse(
    val staffId: Long,
    val nameRu: String?,
    val nameEn: String?,
    val description: String?,
    val posterUrl: String,
    val professionText: String,
    val professionKey: ProfessionKey
)
