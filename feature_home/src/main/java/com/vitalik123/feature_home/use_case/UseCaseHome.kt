package com.vitalik123.feature_home.use_case

import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.network.dto.FilmCollectionResponse

interface UseCaseHome {

    suspend fun getFilmsCollection(type: String): NetworkState<List<FilmUi>?>
}