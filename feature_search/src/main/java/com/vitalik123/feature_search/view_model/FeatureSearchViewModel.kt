package com.vitalik123.feature_search.view_model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.cachedIn
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.feature_search.repository.PagingMoviesSearchPageSource
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class FeatureSearchViewModel @Inject constructor(
    private val pagingSourceFactory: PagingMoviesSearchPageSource.Factory,
) : ViewModel() {

    private val _keyword = MutableStateFlow("")
    val keyword: StateFlow<String> = _keyword.asStateFlow()

    private var newPagingSource: PagingSource<*, *>? = null

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val movies: StateFlow<PagingData<FilmUi>> = keyword
        .map(::newPager)
        .debounce(1500L)
        .flatMapLatest { pager -> pager.flow }
        .cachedIn(viewModelScope)
        .stateIn(viewModelScope, SharingStarted.Lazily, PagingData.empty())

    private fun newPager(keyword: String): Pager<Int, FilmUi> {
        return Pager(PagingConfig(20, enablePlaceholders = false)) {
            pagingSourceFactory.create(keyword).also { newPagingSource = it }
        }
    }

    fun setKeyword(keyword: String) {
        _keyword.tryEmit(keyword)
    }

}