package com.vitalik123.movieappotus.navigation

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.vitalik123.core.utils.TextApp
import com.vitalik123.core_api.CoreFacadeComponentProviders
import com.vitalik123.database.di.RoomFacadeComponentProviders
import com.vitalik123.feature_details.di.DaggerFeatureDetailsComponent
import com.vitalik123.feature_details.presentation.DetailsScreen
import com.vitalik123.feature_details.view_model.FeatureDetailsViewModel
import com.vitalik123.feature_full_list.di.DaggerFeatureFullListComponent
import com.vitalik123.feature_full_list.presentation.FullListScreen
import com.vitalik123.feature_full_list.view_model.FeatureFullListViewModel
import com.vitalik123.feature_home.di.DaggerFeatureHomeComponent
import com.vitalik123.feature_home.presentation.HomeScreen
import com.vitalik123.feature_home.view_model.FeatureHomeViewModel
import com.vitalik123.feature_search.di.DaggerFeatureSearchComponent
import com.vitalik123.feature_search.presentation.SearchScreen
import com.vitalik123.feature_search.view_model.FeatureSearchViewModel
import com.vitalik123.network.di.NetworkFacadeComponentProviders

@SuppressLint("UnusedContentLambdaTargetStateParameter")
@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun NavGraph(
    coreComponent: CoreFacadeComponentProviders,
    networkComponent: NetworkFacadeComponentProviders,
    roomComponent: RoomFacadeComponentProviders,
    modifier: Modifier = Modifier
) {

    val navHostController = rememberNavController()

    SharedTransitionLayout {
        AnimatedContent(true) {

            NavHost(
                navController = navHostController,
                startDestination = Screen.Home,
                modifier = modifier
            ) {

                composable<Screen.Home> {
                    val component = DaggerFeatureHomeComponent.factory().create(
                        coreFacadeComponentProviders = coreComponent,
                        networkFacadeComponentProviders = networkComponent
                    )
                    val viewModel: FeatureHomeViewModel =
                        viewModel(factory = component.getViewModelFactory())
                    HomeScreen(
                        viewModel = viewModel,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this,
                        onClickWatchList = {
                            navHostController.navigate(
                                Screen.FullList(
                                    typeRemote = "",
                                    isWatchlist = true,
                                    title = TextApp.WATCHLIST
                                )
                            )
                        },
                        onClickRateList = {
                            navHostController.navigate(
                                Screen.FullList(
                                    typeRemote = "",
                                    isRatelist = true,
                                    title = TextApp.YOUR_RATE
                                )
                            )
                        },
                        onClickSearch = { navHostController.navigate(Screen.Search) },
                        onClickMore = { type, title ->
                            navHostController.navigate(
                                Screen.FullList(
                                    typeRemote = type,
                                    title = title
                                )
                            )
                        },
                        onClickItem = { navHostController.navigate(Screen.Details(it)) }
                    )
                }

                composable<Screen.Details> { entry ->
                    val component = DaggerFeatureDetailsComponent.factory().create(
                        coreFacadeComponentProviders = coreComponent,
                        networkFacadeComponentProviders = networkComponent,
                        roomFacadeComponentProviders = roomComponent
                    )
                    val viewModel: FeatureDetailsViewModel =
                        viewModel(factory = component.getViewModelFactory())
                    val id = entry.toRoute<Screen.Details>().id

                    DetailsScreen(
                        id = id,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this,
                        viewModel = viewModel,
                        onClickBack = navHostController::popBackStack,
                        onClickItem = { navHostController.navigate(Screen.Details(it)) }
                    )
                }

                composable<Screen.FullList> { entry ->
                    val component = DaggerFeatureFullListComponent.factory().create(
                        coreFacadeComponentProviders = coreComponent,
                        networkFacadeComponentProviders = networkComponent,
                        roomFacadeComponentProviders = roomComponent
                    )
                    val viewModel: FeatureFullListViewModel =
                        viewModel(factory = component.getViewModelFactory())
                    val type = entry.toRoute<Screen.FullList>().typeRemote
                    val isWatchlist = entry.toRoute<Screen.FullList>().isWatchlist
                    val isRatelist = entry.toRoute<Screen.FullList>().isRatelist
                    val title = entry.toRoute<Screen.FullList>().title

                    FullListScreen(
                        title = title,
                        isWatchlist = isWatchlist,
                        isRatelist = isRatelist,
                        type = type,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this,
                        viewModel = viewModel,
                        onClickBack = navHostController::popBackStack,
                        onClickFilm = { navHostController.navigate(Screen.Details(it)) }
                    )
                }

                composable<Screen.Search> {
                    val component = DaggerFeatureSearchComponent.factory().create(
                        coreFacadeComponentProviders = coreComponent,
                        networkFacadeComponentProviders = networkComponent
                    )
                    val viewModel: FeatureSearchViewModel =
                        viewModel(factory = component.getViewModelFactory())

                    SearchScreen(
                        viewModel = viewModel,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this,
                        onClickBack = navHostController::popBackStack,
                        onClickFilm = { navHostController.navigate(Screen.Details(it)) }
                    )
                }
            }
        }
    }
}