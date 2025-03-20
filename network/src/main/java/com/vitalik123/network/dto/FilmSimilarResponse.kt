package com.vitalik123.network.dto

data class FilmSimilarResponse(
    val total: Int,
    val items: List<FilmRelatedResponse>
)
