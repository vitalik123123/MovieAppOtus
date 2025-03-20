package com.vitalik123.feature_home.presentation.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.TextApp
import com.vitalik123.ui_kit.components.FilmItemForHome
import com.vitalik123.ui_kit.components.RowHomeShimmer
import com.vitalik123.ui_kit.theme.LocalColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun HomeList(
    title: String,
    list: List<FilmUi>?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onClickMore: () -> Unit,
    onClickItem: (Long) -> Unit
) {

    Column(modifier = modifier) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Title(
                title = title, modifier = Modifier
                    .weight(1f)
                    .padding(12.dp)
            )

            MoreButton(
                modifier = Modifier
                    .padding(12.dp),
                onClickMore = onClickMore
            )
        }

        if (list.isNullOrEmpty())
            RowHomeShimmer()
        else LazyRow(
            modifier = Modifier
        ) {
            items(list, key = { it.id }) {
                FilmItemForHome(
                    film = it,
                    onClickFilm = onClickItem,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
private fun Title(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        modifier = modifier,
        fontSize = 24.sp,
        color = LocalColor.current.textColorMode
    )
}

@Composable
private fun MoreButton(
    modifier: Modifier = Modifier,
    onClickMore: () -> Unit
) {
    Text(
        text = TextApp.MORE,
        modifier = modifier.clickable(onClick = onClickMore),
        fontSize = 18.sp,
        color = LocalColor.current.redMain
    )
}