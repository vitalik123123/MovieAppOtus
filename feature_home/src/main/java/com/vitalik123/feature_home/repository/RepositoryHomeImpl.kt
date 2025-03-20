package com.vitalik123.feature_home.repository

import com.vitalik123.core.utils.NetworkState
import com.vitalik123.network.api.MovieApi
import com.vitalik123.network.dto.FilmCollectionResponse
import javax.inject.Inject

internal class RepositoryHomeImpl @Inject constructor(
    val api: MovieApi
) : RepositoryHome {

    override suspend fun getFilmsCollections(type: String): NetworkState<FilmCollectionResponse?> =
        runCatching {
            api.getTopFilms(type = type, page = 1)
        }.mapCatching { response ->
            if (response.isSuccessful)
                NetworkState.Success(response.body())
            else NetworkState.ServerError(Throwable(response.message()))
        }.getOrElse { NetworkState.Error(it) }
}