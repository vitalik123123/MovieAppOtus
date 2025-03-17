package com.vitalik123.repository_home.repository

import com.vitalik123.core.utils.NetworkState
import com.vitalik123.network.api.MovieApi
import com.vitalik123.network.dto.FilmCollectionResponse
import retrofit2.Response
import javax.inject.Inject

interface RepositoryHome {

    suspend fun getFilmsCollections(type: String): NetworkState<FilmCollectionResponse?>
}