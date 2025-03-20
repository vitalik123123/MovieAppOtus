package com.vitalik123.feature_full_list.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.database.entity.RatelistEntity
import com.vitalik123.database.entity.WatchlistEntity
import com.vitalik123.database.repository.ratelist.RatelistRoomRepository
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepository
import com.vitalik123.feature_full_list.repository.PagingMoviesTopPageSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class FeatureFullListViewModel @Inject constructor(
    private val watchlistRoomRepository: WatchlistRoomRepository,
    private val ratelistRoomRepository: RatelistRoomRepository,
    private val pagingSourceFactory: PagingMoviesTopPageSource.Factory,
) : ViewModel() {

    private val _state = MutableStateFlow(FeatureFullListState())
    val state = _state.asStateFlow()

    private val _type = MutableStateFlow("")
    val type: StateFlow<String> = _type.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    @OptIn(ExperimentalCoroutinesApi::class)
    val movies: StateFlow<PagingData<FilmUi>> = type
        .map(::newPager)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(type: String): Pager<Int, FilmUi> {
        return Pager(PagingConfig(35, enablePlaceholders = false)) {
            pagingSourceFactory.create(type).also { newPagingSource = it }
        }
    }

    fun setType(type: String) {
        _type.tryEmit(type)
    }

    fun define(isWatchlist: Boolean, isRatelist: Boolean, type: String) {
        when {
            isWatchlist -> getAllFromWatchlist()
            isRatelist -> getAllFromRatelist()
            else -> setType(type)
        }
    }

    fun getAllFromWatchlist() = viewModelScope.launch {
        _state.update { ui ->
            ui.copy(filmsFromWatchlist = watchlistRoomRepository.getAllWatchlist())
        }
    }

    fun getAllFromRatelist() = viewModelScope.launch {
        _state.update { ui ->
            ui.copy(filmsFromRatelist = ratelistRoomRepository.getAllLocalRatinglist())
        }
    }

    fun deleteAllWatchlist() = viewModelScope.launch {
        watchlistRoomRepository.deleteAll()
        _state.update { ui ->
            ui.copy(filmsFromWatchlist = emptyList())
        }
    }

    fun deleteAllRatelist() = viewModelScope.launch {
        ratelistRoomRepository.deleteAll()
        _state.update { ui ->
            ui.copy(filmsFromRatelist = emptyList())
        }
    }

    data class FeatureFullListState(
        val filmsFromWatchlist: List<WatchlistEntity> = emptyList(),
        val filmsFromRatelist: List<RatelistEntity> = emptyList()
    )
}