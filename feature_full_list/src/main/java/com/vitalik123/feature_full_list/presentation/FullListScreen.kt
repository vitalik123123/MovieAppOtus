package com.vitalik123.feature_full_list.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.vitalik123.feature_full_list.presentation.components.FilmForFullList
import com.vitalik123.feature_full_list.presentation.components.TopBarFullList
import com.vitalik123.feature_full_list.view_model.FeatureFullListViewModel
import com.vitalik123.ui_kit.extentions.itemsPaging
import com.vitalik123.ui_kit.theme.LocalColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FullListScreen(
    modifier: Modifier = Modifier,
    title: String,
    isWatchlist: Boolean = false,
    isRatelist: Boolean = false,
    type: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: FeatureFullListViewModel,
    onClickBack: () -> Unit = {},
    onClickFilm: (Long) -> Unit = {}
) {

    val state = viewModel.state.collectAsState()
    val films = viewModel.movies.collectAsLazyPagingItems()
    LaunchedEffect(Unit) {
        viewModel.define(isWatchlist, isRatelist, type)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LocalColor.current.backgroundMode)
            .padding(12.dp)
    ) {

        TopBarFullList(
            title = title,
            onClickBack = onClickBack,
            onClickDelete = {
                when {
                    isWatchlist -> viewModel.deleteAllWatchlist()
                    isRatelist -> viewModel.deleteAllRatelist()
                    else -> {}
                }
            },
            isVisibleDelete = isWatchlist || isRatelist
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 12.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            when {

                isWatchlist -> items(
                    state.value.filmsFromWatchlist,
                    key = { it.kinopoiskId }) { item ->
                    FilmForFullList(
                        poster = item.poster,
                        id = item.kinopoiskId,
                        title = item.title,
                        rating = item.rating,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        onClickItem = onClickFilm
                    )
                }
                isRatelist -> items(
                    state.value.filmsFromRatelist,
                    key = { it.kinopoiskId }) { item ->
                    FilmForFullList(
                        poster = item.poster,
                        id = item.kinopoiskId,
                        title = item.title,
                        rating = item.myRating.toDouble(),
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        onClickItem = onClickFilm
                    )
                }
                else -> itemsPaging(films, key = { it.id }) { item ->
                    FilmForFullList(
                        poster = item.poster.orEmpty(),
                        id = item.id,
                        title = item.title,
                        rating = item.rating,
                        sharedTransitionScope = sharedTransitionScope,
                        animatedContentScope = animatedContentScope,
                        onClickItem = onClickFilm
                    )
                }
            }
        }

        Spacer(Modifier.navigationBarsPadding())
    }

}