package com.vitalik123.repository_home.use_case

import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.repository_home.mapper.toFilmUi
import com.vitalik123.repository_home.repository.RepositoryHome
import javax.inject.Inject

class UseCaseHomeImpl @Inject constructor(
    val repository: RepositoryHome
) : UseCaseHome{

    override suspend fun getFilmsCollection(type: String): NetworkState<List<FilmUi>?> {
        return when(val response = repository.getFilmsCollections(type)){
            is NetworkState.Error ->  NetworkState.Error(response.error)
            is NetworkState.ServerError -> NetworkState.ServerError(response.error)
            is NetworkState.Success -> {
                NetworkState.Success(response.data?.items?.map { it.toFilmUi() })
            }
        }
    }
}