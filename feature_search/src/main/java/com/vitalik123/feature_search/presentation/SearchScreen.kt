package com.vitalik123.feature_search.presentation

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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.compose.collectAsLazyPagingItems
import com.vitalik123.feature_search.presentation.components.SearchElement
import com.vitalik123.feature_search.presentation.components.SearchItem
import com.vitalik123.feature_search.presentation.components.TopBarSearch
import com.vitalik123.feature_search.view_model.FeatureSearchViewModel
import com.vitalik123.ui_kit.extentions.itemsPaging
import com.vitalik123.ui_kit.theme.LocalColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: FeatureSearchViewModel,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClickBack: () -> Unit = {},
    onClickFilm: (Long) -> Unit = {}
) {

    val films = viewModel.movies.collectAsLazyPagingItems()
    val searchText = viewModel.keyword.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LocalColor.current.backgroundMode)
    ) {

        TopBarSearch(onBackClick = onClickBack)

        SearchElement(
            searchText = searchText.value,
            changeTextSearch = viewModel::setKeyword,
            modifier = Modifier.padding(12.dp),
        )

        LazyVerticalGrid(
            modifier = Modifier.padding(top = 12.dp),
            columns = GridCells.Fixed(2),
            verticalArrangement = Arrangement.spacedBy(12.dp),
        ) {
            itemsPaging(films, key = { it.id }) { item ->
                SearchItem(
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

        Spacer(Modifier.navigationBarsPadding())
    }
}