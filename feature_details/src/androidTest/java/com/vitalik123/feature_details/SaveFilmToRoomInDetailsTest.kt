package com.vitalik123.feature_details

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vitalik123.database.repository.ratelist.RatelistRoomRepository
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepository
import com.vitalik123.feature_details.presentation.DetailsScreen
import com.vitalik123.feature_details.use_case.UseCaseDetails
import com.vitalik123.feature_details.use_case.UseCaseDetailsImpl
import com.vitalik123.feature_details.view_model.FeatureDetailsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@OptIn(ExperimentalCoroutinesApi::class)
@RunWith(AndroidJUnit4::class)
class SaveFilmToRoomInDetailsTest {

    lateinit var mockUseCase: UseCaseDetails
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @get:Rule
    val composeTestRule = createComposeRule()


    @SuppressLint("UnusedContentLambdaTargetStateParameter")
    @OptIn(ExperimentalSharedTransitionApi::class)
    @Test
    fun saveFilmTest() = runTest(testDispatcher) {

        val viewModelMock =
            FeatureDetailsViewModel(useCase = mockUseCase)

        composeTestRule.setContent {
            SharedTransitionLayout {
                AnimatedContent(true) {
                    DetailsScreen(
                        id = 1,
                        sharedTransitionScope = this@SharedTransitionLayout,
                        animatedContentScope = this,
                        viewModel = viewModelMock,
                        onClickBack = {},
                        onClickItem = {}
                    )
                }
            }
        }


        composeTestRule.onNodeWithTag("save_button").assertExists()
    }
}