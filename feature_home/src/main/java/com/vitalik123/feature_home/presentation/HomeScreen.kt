package com.vitalik123.feature_home.presentation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import com.vitalik123.feature_home.presentation.content.HomeContent
import com.vitalik123.feature_home.view_model.FeatureHomeViewModel

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: FeatureHomeViewModel,
    onClickSearch: () -> Unit,
    onClickWatchList: () -> Unit,
    onClickRateList: () -> Unit,
    onClickMore: (String, String) -> Unit,
    onClickItem: (Long) -> Unit
) {
    val state = viewModel.state.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.getFilms()
    }

    HomeContent(
        listPopularFilm = state.value.popularMoviesList,
        listPopularSeries = state.value.popularSeriesList,
        list250Movies = state.value.top250MoviesList,
        list250Series = state.value.top250SeriesList,
        sharedTransitionScope = sharedTransitionScope,
        animatedContentScope = animatedContentScope,
        modifier = modifier,
        onClickWatchlist = onClickWatchList,
        onClickRatelist = onClickRateList,
        onClickSearch = onClickSearch,
        onClickMore = onClickMore,
        onClickItem = onClickItem
    )
}