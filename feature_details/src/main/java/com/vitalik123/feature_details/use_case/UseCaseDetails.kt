package com.vitalik123.feature_details.use_case

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.feature_details.dto.StaffUi

interface UseCaseDetails {

    suspend fun getFilmDetails(id: Long): NetworkState<FilmDetailsUi?>

    suspend fun getStaff(id: Long): NetworkState<List<StaffUi>?>

    suspend fun getOtherSeriesFilms(id: Long): NetworkState<List<FilmUi>?>

    suspend fun getSimilar(id: Long): NetworkState<List<FilmUi>?>
}