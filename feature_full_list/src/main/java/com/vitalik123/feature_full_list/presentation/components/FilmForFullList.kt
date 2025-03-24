package com.vitalik123.feature_full_list.presentation.components

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.vitalik123.ui_kit.theme.LocalColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun FilmForFullList(
    modifier: Modifier = Modifier,
    poster: String,
    id: Long,
    title: String,
    rating: Double?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClickItem: (Long) -> Unit
) {
    with(sharedTransitionScope) {
        Column(
            modifier = modifier
                .padding(top = 16.dp)
                .wrapContentSize()
                .clickable(
                    onClick = {
                        onClickItem.invoke(id)
                    })
        ) {
            Box {
                AsyncImage(
                    model = poster,
                    contentDescription = null,
                    modifier = Modifier
                        .size(170.dp, 250.dp)
                        .sharedElement(
                            state = rememberSharedContentState(key = poster.toString()),
                            animatedVisibilityScope = animatedContentScope
                        )
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.FillHeight
                )

                if (rating != null)
                    Text(
                        text = rating.toString(),
                        modifier = Modifier
                            .padding(10.dp)
                            .align(Alignment.TopStart)
                            .clip(RoundedCornerShape(10.dp))
                            .background(LocalColor.current.kinopoiskOrange)
                            .sharedElement(
                                state = rememberSharedContentState(
                                    key = rating.toString()
                                ), animatedVisibilityScope = animatedContentScope
                            )
                            .padding(6.dp),
                        fontSize = 16.sp,
                        color = LocalColor.current.whiteAlways
                    )
            }

            Text(
                text = title,
                modifier = Modifier
                    .padding(top = 6.dp, start = 10.dp, end = 10.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = title),
                        animatedVisibilityScope = animatedContentScope
                    )
                    .widthIn(0.dp, 150.dp)
                    .align(Alignment.CenterHorizontally),
                minLines = 2,
                maxLines = 2,
                textAlign = TextAlign.Center,
                color = LocalColor.current.textColorMode,
                fontSize = 14.sp
            )
        }
    }
}