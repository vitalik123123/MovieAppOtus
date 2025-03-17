package com.vitalik123.feature_home.view_model

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.core_api.annotation.FeatureHomeScope
import com.vitalik123.repository_home.use_case.UseCaseHome
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureHomeScope
class FeatureHomeViewModel @Inject constructor(
    private val useCase: UseCaseHome
) : ViewModel() {

    val state = MutableStateFlow(FeatureHomeState())

    init {
        getFilm()
    }

    fun getFilm() = viewModelScope.launch {
        when(val response = useCase.getFilmsCollection("TOP_100_POPULAR_FILMS")){
            is NetworkState.Error -> {

            }
            is NetworkState.ServerError-> {}
            is NetworkState.Success -> {
                state.update { ui ->
                    ui.copy(film = response.data?.first())
                }
            }
        }
    }

    data class FeatureHomeState(
        val film: FilmUi? = null
    )

}