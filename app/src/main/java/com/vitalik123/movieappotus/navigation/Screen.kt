package com.vitalik123.movieappotus.navigation

import kotlinx.serialization.Serializable

@Serializable
sealed class Screen {
    @Serializable
    data object Home : Screen()

    @Serializable
    data class Details(val id: Long) : Screen()

    @Serializable
    data class FullList(
        val typeRemote: String,
        val isWatchlist: Boolean = false,
        val isRatelist: Boolean = false,
        val title: String
    )

    @Serializable
    data object Search : Screen()
}