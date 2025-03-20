package com.vitalik123.core.dto.ui

data class FilmDetailsUi(
    val id: Long,
    val title: String,
    val rating: Double?,
    val poster: String?,
    val year: String,
    val description: String,
    val titleAndYear: String
)
