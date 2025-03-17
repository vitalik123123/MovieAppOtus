package com.vitalik123.repository_home.use_case

import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState

interface UseCaseHome {

    suspend fun getFilmsCollection(type: String): NetworkState<List<FilmUi>?>
}