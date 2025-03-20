package com.vitalik123.feature_details.presentation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.vitalik123.core.R
import com.vitalik123.core.utils.TextApp
import com.vitalik123.feature_details.presentation.components.Description
import com.vitalik123.feature_details.presentation.components.DetailsContent
import com.vitalik123.feature_details.presentation.components.SimilarMoviesList
import com.vitalik123.feature_details.presentation.components.StaffList
import com.vitalik123.feature_details.presentation.components.TopBarDetails
import com.vitalik123.feature_details.view_model.FeatureDetailsViewModel
import com.vitalik123.ui_kit.theme.LocalColor

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun DetailsScreen(
    modifier: Modifier = Modifier,
    id: Long,
    sharedTransitionScope: SharedTransitionScope,
    animatedContentScope: AnimatedContentScope,
    viewModel: FeatureDetailsViewModel,
    onClickBack: () -> Unit = {},
    onClickItem: (Long) -> Unit
) {

    val state = viewModel.state.collectAsState()
    val scrollState = rememberScrollState()
    val openRate = remember { mutableStateOf(false) }

    LaunchedEffect(Unit) {
        viewModel.getData(id)
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .background(LocalColor.current.backgroundMode)
    ) {

        TopBarDetails(modifier = Modifier.padding(12.dp), onBackClick = onClickBack)

        Column(
            modifier = Modifier.verticalScroll(scrollState)
        ) {
            DetailsContent(
                modifier = Modifier.padding(12.dp),
                sharedTransitionScope = sharedTransitionScope,
                animatedContentScope = animatedContentScope,
                onClickWatchlist = {
                    if (state.value.isSaveWatchlist)
                        viewModel.deleteFilmFromWatchlist()
                    else viewModel.saveFilmToWatchlist()
                },
                isSave = state.value.isSaveWatchlist,
                myRate = state.value.myRateFromRoom,
                film = state.value.filmDetails,
                onClickMyRate = {
                    openRate.value = true
                }
            )

            Description(
                modifier = Modifier.padding(12.dp),
                description = state.value.filmDetails?.description.orEmpty()
            )

            StaffList(list = state.value.listStaff, modifier = Modifier.padding(12.dp))

            if ((state.value.listOtherSeries.isEmpty().not()))
                SimilarMoviesList(
                    title = TextApp.OTHER_SERIES,
                    list = state.value.listOtherSeries,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    onClickItem = onClickItem
                )

            if ((state.value.listSimilar.isEmpty().not()))
                SimilarMoviesList(
                    title = TextApp.SIMILAR,
                    list = state.value.listSimilar,
                    sharedTransitionScope = sharedTransitionScope,
                    animatedContentScope = animatedContentScope,
                    onClickItem = onClickItem
                )

            Spacer(Modifier.navigationBarsPadding())
        }

        if (openRate.value)
            OpenRate(
                onClose = { openRate.value = false },
                onClickSave = { viewModel.saveRate(id, it) }
            )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OpenRate(
    onClose: () -> Unit,
    onClickSave: (Int?) -> Unit,
    modifier: Modifier = Modifier
) {

    val chooseRate: MutableState<Int?> = remember { mutableStateOf(null) }

    ModalBottomSheet(modifier = modifier, onDismissRequest = onClose) {

        Column {

            Text(
                TextApp.RATE_IT_2,
                fontSize = 20.sp,
                modifier = Modifier.align(Alignment.CenterHorizontally)
            )

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 20.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                repeat(10) {
                    Icon(
                        painter = painterResource(
                            if (chooseRate.value != null && (chooseRate.value ?: 0) >= it)
                                R.drawable.ic_star_full
                            else R.drawable.ic_star_empty
                        ),
                        tint = LocalColor.current.redMain,
                        modifier = Modifier.clickable(onClick = { chooseRate.value = it }),
                        contentDescription = null
                    )
                }
            }

            Text(
                text = TextApp.SAVE,
                modifier = Modifier
                    .padding(top = 18.dp)
                    .padding(horizontal = 16.dp)
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(12.dp))
                    .clickable(
                        onClick = {
                            if (chooseRate.value != null)
                                onClickSave.invoke(chooseRate.value?.plus(1))
                                    .apply { onClose.invoke() }
                        }
                    )
                    .background(
                        if (chooseRate.value == null) LocalColor.current.redMain.copy(alpha = 0.5f)
                        else LocalColor.current.redMain
                    )
                    .padding(vertical = 12.dp),
                color = LocalColor.current.whiteAlways,
                fontSize = 26.sp,
                textAlign = TextAlign.Center
            )

            Spacer(Modifier.size(50.dp))
        }
    }
}