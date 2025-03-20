package com.vitalik123.feature_home.presentation.content

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.Constants
import com.vitalik123.core.utils.TextApp
import com.vitalik123.feature_home.presentation.components.HomeList
import com.vitalik123.feature_home.presentation.components.HomeTopBar
import com.vitalik123.ui_kit.theme.LocalColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeContent(
    listPopularFilm: List<FilmUi>?,
    listPopularSeries: List<FilmUi>?,
    list250Movies: List<FilmUi>?,
    list250Series: List<FilmUi>?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onClickWatchlist: () -> Unit = {},
    onClickRatelist: () -> Unit = {},
    onClickSearch: () -> Unit = {},
    onClickMore: (String, String) -> Unit,
    onClickItem: (Long) -> Unit = {}
) {

    val scrollState = rememberScrollState()

    Column(
        modifier
            .background(LocalColor.current.backgroundMode)
    ) {

        HomeTopBar(
            onClickWatchlist = onClickWatchlist,
            onClickRatelist = onClickRatelist,
            onClickSearch = onClickSearch
        )

        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
        ) {
            HomeList(
                title = TextApp.TOP_FILM,
                list = listPopularFilm,
                modifier = Modifier,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                onClickMore = {
                    onClickMore.invoke(
                        Constants.FILM_COLLECTION_TYPE_TOP_POPULAR_MOVIES,
                        TextApp.TOP_FILM
                    )
                },
                onClickItem = onClickItem
            )

            HomeList(
                title = TextApp.TOP_TV_SHOW,
                list = listPopularSeries,
                modifier = Modifier,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                onClickMore = {
                    onClickMore.invoke(
                        Constants.FILM_COLLECTION_TYPE_POPULAR_SERIES,
                        TextApp.TOP_TV_SHOW
                    )
                },
                onClickItem = onClickItem
            )

            HomeList(
                title = TextApp.TOP_250_MOVIES,
                list = list250Movies,
                modifier = Modifier,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                onClickMore = {
                    onClickMore.invoke(
                        Constants.FILM_COLLECTION_TYPE_TOP_250_MOVIES,
                        TextApp.TOP_250_MOVIES
                    )
                },
                onClickItem = onClickItem
            )

            HomeList(
                title = TextApp.TOP_250_SHOW,
                list = list250Series,
                modifier = Modifier,
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                onClickMore = {
                    onClickMore.invoke(
                        Constants.FILM_COLLECTION_TYPE_TOP_250_TV_SHOW,
                        TextApp.TOP_250_SHOW
                    )
                },
                onClickItem = onClickItem
            )


            Spacer(Modifier.navigationBarsPadding())
        }
    }
}