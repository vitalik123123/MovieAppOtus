package com.vitalik123.feature_details.repository

import com.vitalik123.core.utils.NetworkState
import com.vitalik123.network.dto.FilmCastResponse
import com.vitalik123.network.dto.FilmDetailsResponse
import com.vitalik123.network.dto.FilmRelatedResponse
import com.vitalik123.network.dto.FilmSimilarResponse

interface RepositoryDetails {

    suspend fun getFilmDetails(id: Long): NetworkState<FilmDetailsResponse?>

    suspend fun getFilmStaff(id: Long): NetworkState<List<FilmCastResponse>?>

    suspend fun getOtherSeries(id: Long): NetworkState<List<FilmRelatedResponse>?>

    suspend fun getSimilar(id: Long): NetworkState<FilmSimilarResponse?>
}