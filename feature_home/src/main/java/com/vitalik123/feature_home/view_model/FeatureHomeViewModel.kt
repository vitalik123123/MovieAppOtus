package com.vitalik123.feature_home.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.Constants
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.feature_home.use_case.UseCaseHome
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject


class FeatureHomeViewModel @Inject constructor(
    private val useCase: UseCaseHome
) : ViewModel() {

    private val _state = MutableStateFlow(FeatureHomeState())
    val state = _state.asStateFlow()

    fun getFilms() = viewModelScope.async {
        getPopularMovies()
        getPopularSeries()
        getTop250Movies()
        getTop250Series()
    }

    fun getPopularMovies() = viewModelScope.launch {
        when (val response =
            useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_TOP_POPULAR_MOVIES)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(popularMoviesList = response.data)
                }
            }
        }
    }

    fun getPopularSeries() = viewModelScope.launch {
        when (val response =
            useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_POPULAR_SERIES)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(popularSeriesList = response.data)
                }
            }
        }
    }

    fun getTop250Movies() = viewModelScope.launch {
        when (val response =
            useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_TOP_250_MOVIES)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(top250MoviesList = response.data)
                }
            }
        }
    }

    fun getTop250Series() = viewModelScope.launch {
        when (val response =
            useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_TOP_250_TV_SHOW)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(top250SeriesList = response.data)
                }
            }
        }
    }

    data class FeatureHomeState(
        val popularMoviesList: List<FilmUi>? = null,
        val popularSeriesList: List<FilmUi>? = null,
        val top250MoviesList: List<FilmUi>? = null,
        val top250SeriesList: List<FilmUi>? = null,
    )

}