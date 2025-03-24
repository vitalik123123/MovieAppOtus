package com.vitalik123.feature_details.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.database.repository.ratelist.RatelistRoomRepository
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepository
import com.vitalik123.feature_details.dto.StaffUi
import com.vitalik123.feature_details.use_case.UseCaseDetails
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeatureDetailsViewModel @Inject constructor(
    private val useCase: UseCaseDetails,
    private val watchlistRoomRepository: WatchlistRoomRepository,
    private val ratelistRoomRepository: RatelistRoomRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(FeatureDetailsState())
    val state = _state.asStateFlow()

    fun getData(id: Long) = viewModelScope.async {
        getFilmDetails(id)
        getStaff(id)
        getOtherSeries(id)
        getSimilar(id)
        getMyRateFromRoom(id)
    }

    fun getFilmDetails(id: Long) = viewModelScope.launch {
        if (watchlistRoomRepository.existsMovieToWatchlist(id))
            _state.update { ui ->
                ui.copy(
                    filmDetails = watchlistRoomRepository.getMovieFromWatchlist(id),
                    isSaveWatchlist = true
                )
            }
        when (val response = useCase.getFilmDetails(id)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(filmDetails = response.data)
                }
            }
        }
    }

    fun saveTest(filmMock: FilmDetailsUi) = viewModelScope.launch {
        _state.update { ui -> ui.copy(filmDetails = filmMock) }
    }

    fun getStaff(id: Long) = viewModelScope.launch {
        when (val response = useCase.getStaff(id)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(listStaff = response.data ?: emptyList())
                }
            }
        }
    }

    fun getOtherSeries(id: Long) = viewModelScope.launch {
        when (val response = useCase.getOtherSeriesFilms(id)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(listOtherSeries = response.data ?: emptyList())
                }
            }
        }
    }

    fun getSimilar(id: Long) = viewModelScope.launch {
        when (val response = useCase.getSimilar(id)) {
            is NetworkState.Error -> {}
            is NetworkState.ServerError -> {}
            is NetworkState.Success -> {
                _state.update { ui ->
                    ui.copy(listSimilar = response.data ?: emptyList())
                }
            }
        }
    }

    fun saveFilmToWatchlist() = viewModelScope.launch {
        watchlistRoomRepository.saveMovieToWatchlist(_state.value.filmDetails)
        existWatchlist(_state.value.filmDetails?.id ?: -1)
    }

    fun deleteFilmFromWatchlist() = viewModelScope.launch {
        watchlistRoomRepository.deleteMovieFromWatchlist(_state.value.filmDetails?.id ?: -1)
        existWatchlist(_state.value.filmDetails?.id ?: -1)
    }

    fun existWatchlist(id: Long) = viewModelScope.launch {
        _state.update { ui ->
            ui.copy(
                isSaveWatchlist = watchlistRoomRepository.existsMovieToWatchlist(id)
            )
        }
    }

    fun saveRate(id: Long, rate: Int?) = viewModelScope.launch {
        if (rate != null && _state.value.filmDetails != null) {
            ratelistRoomRepository.deleteMyRatingFromRatinglist(id)
            ratelistRoomRepository.saveMyRatingToRatinglist(
                _state.value.filmDetails!!,
                myRating = rate
            )
            _state.update { ui -> ui.copy(myRateFromRoom = rate) }
        }

    }

    fun getMyRateFromRoom(id: Long) = viewModelScope.launch {
        if (ratelistRoomRepository.existsMyRatingToRatinglist(id))
            _state.update { ui ->
                ui.copy(
                    myRateFromRoom = ratelistRoomRepository.getMyRatingFromRatinglistToDetails(id).myRating
                )
            }
    }

    data class FeatureDetailsState(
        val filmDetails: FilmDetailsUi? = null,
        val listStaff: List<StaffUi> = emptyList(),
        val listOtherSeries: List<FilmUi> = emptyList(),
        val listSimilar: List<FilmUi> = emptyList(),
        val isSaveWatchlist: Boolean = false,
        val myRateFromRoom: Int? = null,
    )
}