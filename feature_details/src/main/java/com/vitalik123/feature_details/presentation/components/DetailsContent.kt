package com.vitalik123.feature_details.presentation.components

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import com.vitalik123.core.R
import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.core.utils.TextApp
import com.vitalik123.ui_kit.theme.LocalColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsContent(
    modifier: Modifier = Modifier,
    film: FilmDetailsUi?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    onClickWatchlist: () -> Unit,
    myRate: Int?,
    isSave: Boolean = false,
    onClickMyRate: () -> Unit
) {

    val isOpen = remember { mutableStateOf(false) }

    with(sharedTransitionScope) {
        Row(
            modifier = modifier.background(LocalColor.current.backgroundMode)
        ) {
            if (isOpen.value.not())
                ImageMini(
                    poster = film?.poster.orEmpty(),
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    onClick = { isOpen.value = true }
                )

            Column(
                modifier = Modifier.padding(start = 24.dp, end = 12.dp, top = 24.dp)
            ) {
                Text(
                    modifier = Modifier.sharedElement(
                        state = rememberSharedContentState(key = film?.title.orEmpty()),
                        animatedVisibilityScope = animatedContentScope
                    ),
                    text = film?.titleAndYear.orEmpty(),
                    color = LocalColor.current.textColorMode,
                    fontSize = 18.sp,
                    textAlign = TextAlign.Center
                )

                RateRow(
                    modifier = Modifier.padding(top = 12.dp),
                    kinopoiskRate = film?.rating,
                    myRate = myRate,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    onClickMyRate = onClickMyRate
                )

                IconButton(
                    onClick = onClickWatchlist,
                    modifier = Modifier
                        .padding(12.dp)
                        .wrapContentWidth()
                        .align(Alignment.CenterHorizontally)
                ) {
                    Icon(
                        modifier = Modifier
                            .size(42.dp)
                            .testTag("save_button"),
                        painter = painterResource(if (isSave) R.drawable.ic_bookmark_full else R.drawable.ic_bookmark_empty),
                        contentDescription = null,
                        tint = LocalColor.current.redMain
                    )
                }
            }
        }
    }

    if (isOpen.value)
        FullDialog(film?.poster.orEmpty(), isClose = { isOpen.value = false })
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun ImageMini(
    poster: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    with(sharedTransitionScope) {
        AsyncImage(
            model = poster,
            contentDescription = null,
            modifier = modifier
                .size(170.dp, 250.dp)
                .sharedElement(
                    state = rememberSharedContentState(key = poster.toString()),
                    animatedVisibilityScope = animatedContentScope
                )
                .clip(RoundedCornerShape(14.dp))
                .clickable(onClick = onClick),
            contentScale = ContentScale.FillHeight
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun RateRow(
    kinopoiskRate: Double?,
    myRate: Int?,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onClickMyRate: () -> Unit
) {

    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceAround
    ) {

        Rate(
            rate = kinopoiskRate,
            rateColor = LocalColor.current.kinopoiskOrange,
            emptyRateText = TextApp.MISSING,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope,
            onClickMyRate = {}
        )

        Rate(
            rate = myRate?.toDouble(),
            rateColor = LocalColor.current.redMain,
            emptyRateText = TextApp.RATE_IT,
            sharedTransitionScope = sharedTransitionScope,
            animatedContentScope = animatedContentScope,
            onClickMyRate = onClickMyRate
        )
    }
}

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
private fun Rate(
    rate: Double?,
    rateColor: Color,
    emptyRateText: String,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    modifier: Modifier = Modifier,
    onClickMyRate: () -> Unit
) {

    with(sharedTransitionScope) {
        Column(
            modifier = modifier
                .wrapContentHeight()
                .clickable(onClick = onClickMyRate),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Icon(
                painter = painterResource(if (rate != null) R.drawable.ic_star_full else R.drawable.ic_star_empty),
                contentDescription = null,
                tint = rateColor,
                modifier = Modifier.size(42.dp)
            )

            Text(
                text = rate?.toString() ?: emptyRateText,
                modifier = Modifier.sharedElement(
                    state = rememberSharedContentState(key = rate.toString()),
                    animatedVisibilityScope = animatedContentScope
                ),
                fontSize = if (rate != null) 24.sp else 12.sp,
                color = LocalColor.current.textColorMode
            )

        }
    }
}

@Composable
private fun FullDialog(
    image: String,
    isClose: () -> Unit
) {
    Dialog(
        onDismissRequest = isClose,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = false,
            usePlatformDefaultWidth = false
        )
    ) {

        val scale = remember { mutableFloatStateOf(1f) }
        val rotationState = remember { mutableFloatStateOf(1f) }
        Box(
            modifier = Modifier
                .clip(RectangleShape)
                .fillMaxSize()
                .pointerInput(Unit) {
                    detectTransformGestures { centroid, pan, zoom, rotation ->
                        scale.floatValue *= zoom
                        rotationState.floatValue += rotation
                    }
                }
        ) {
            Image(
                modifier = Modifier
                    .align(Alignment.Center)
                    .graphicsLayer(
                        scaleX = maxOf(.5f, minOf(3f, scale.floatValue)),
                        scaleY = maxOf(.5f, minOf(3f, scale.floatValue))
                    ),
                contentDescription = null,
                painter = rememberAsyncImagePainter(image)
            )

            IconButton(
                onClick = isClose,
                modifier = Modifier
                    .padding(18.dp)
                    .size(28.dp)
                    .align(Alignment.TopEnd)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_close),
                    contentDescription = null,
                    tint = LocalColor.current.whiteAlways
                )
            }
        }
    }
}