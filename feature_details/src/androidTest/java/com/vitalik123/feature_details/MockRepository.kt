package com.vitalik123.feature_details

import com.vitalik123.core.utils.NetworkState
import com.vitalik123.feature_details.repository.RepositoryDetails
import com.vitalik123.network.dto.FilmDetailsResponse

class MockRepository : RepositoryDetails {
    override suspend fun getFilmDetails(id: Long): NetworkState<FilmDetailsResponse?> =
        NetworkState.Error(Throwable())
}