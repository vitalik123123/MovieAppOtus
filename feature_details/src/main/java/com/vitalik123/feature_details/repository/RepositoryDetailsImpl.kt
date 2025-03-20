package com.vitalik123.feature_details.repository

import com.vitalik123.core.utils.NetworkState
import com.vitalik123.network.api.MovieApi
import com.vitalik123.network.dto.FilmCastResponse
import com.vitalik123.network.dto.FilmDetailsResponse
import com.vitalik123.network.dto.FilmRelatedResponse
import com.vitalik123.network.dto.FilmSimilarResponse
import javax.inject.Inject

class RepositoryDetailsImpl @Inject constructor(
    val api: MovieApi
) : RepositoryDetails{

    override suspend fun getFilmDetails(id: Long): NetworkState<FilmDetailsResponse?> =
        runCatching {
            api.getFilmDetailsById(id)
        }.mapCatching { response ->
            if (response.isSuccessful)
                NetworkState.Success(response.body())
            else NetworkState.ServerError(Throwable(response.message()))
        }.getOrElse { NetworkState.Error(it) }

    override suspend fun getFilmStaff(id: Long): NetworkState<List<FilmCastResponse>?> =
        runCatching {
            api.getFilmCast(id)
        }.mapCatching { response ->
            if (response.isSuccessful)
                NetworkState.Success(response.body())
            else NetworkState.ServerError(Throwable(response.message()))
        }.getOrElse { NetworkState.Error(it) }

    override suspend fun getOtherSeries(id: Long): NetworkState<List<FilmRelatedResponse>?> =
        runCatching {
            api.getFilmSequelsAndPrequels(id)
        }.mapCatching { response ->
            if (response.isSuccessful)
                NetworkState.Success(response.body())
            else NetworkState.ServerError(Throwable(response.message()))
        }.getOrElse { NetworkState.Error(it) }

    override suspend fun getSimilar(id: Long): NetworkState<FilmSimilarResponse?> =
        runCatching {
            api.getFilmSimilars(id)
        }.mapCatching { response ->
            if (response.isSuccessful)
                NetworkState.Success(response.body())
            else NetworkState.ServerError(Throwable(response.message()))
        }.getOrElse { NetworkState.Error(it) }
}