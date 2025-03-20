package com.vitalik123.feature_details.use_case

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.feature_details.dto.StaffUi
import com.vitalik123.feature_details.mapper.toFilmDetailsUi
import com.vitalik123.feature_details.mapper.toFilmUi
import com.vitalik123.feature_details.mapper.toStaffUi
import com.vitalik123.feature_details.repository.RepositoryDetails
import javax.inject.Inject

class UseCaseDetailsImpl @Inject constructor(
    val repository: RepositoryDetails
) : UseCaseDetails {

    override suspend fun getFilmDetails(id: Long): NetworkState<FilmDetailsUi?> {
        return when (val response = repository.getFilmDetails(id)) {
            is NetworkState.Error -> NetworkState.Error(response.error)
            is NetworkState.ServerError -> NetworkState.ServerError(response.error)
            is NetworkState.Success -> {
                NetworkState.Success(response.data?.toFilmDetailsUi())
            }
        }
    }

    override suspend fun getStaff(id: Long): NetworkState<List<StaffUi>?> {
        return when (val response = repository.getFilmStaff(id)) {
            is NetworkState.Error -> NetworkState.Error(response.error)
            is NetworkState.ServerError -> NetworkState.ServerError(response.error)
            is NetworkState.Success -> NetworkState.Success(response.data?.map { it.toStaffUi() })
        }
    }

    override suspend fun getOtherSeriesFilms(id: Long): NetworkState<List<FilmUi>?> {
        return when (val response = repository.getOtherSeries(id)) {
            is NetworkState.Error -> NetworkState.Error(response.error)
            is NetworkState.ServerError -> NetworkState.ServerError(response.error)
            is NetworkState.Success -> NetworkState.Success(response.data?.map { it.toFilmUi() })
        }
    }

    override suspend fun getSimilar(id: Long): NetworkState<List<FilmUi>?> {
        return when (val response = repository.getSimilar(id)) {
            is NetworkState.Error -> NetworkState.Error(response.error)
            is NetworkState.ServerError -> NetworkState.ServerError(response.error)
            is NetworkState.Success -> NetworkState.Success(response.data?.items?.map { it.toFilmUi() })
        }
    }
}