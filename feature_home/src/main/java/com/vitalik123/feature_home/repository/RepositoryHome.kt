package com.vitalik123.feature_home.repository

import com.vitalik123.core.utils.NetworkState
import com.vitalik123.network.dto.FilmCollectionResponse

interface RepositoryHome {

    suspend fun getFilmsCollections(type: String): NetworkState<FilmCollectionResponse?>
}