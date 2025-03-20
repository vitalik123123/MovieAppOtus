package com.vitalik123.feature_home

import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.Constants
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.feature_home.use_case.UseCaseHome
import com.vitalik123.feature_home.view_model.FeatureHomeViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions

@OptIn(ExperimentalCoroutinesApi::class)
class FeatureHomeViewModelTest {

    private lateinit var viewModel: FeatureHomeViewModel
    private val useCase = mock(UseCaseHome::class.java)
    private val testDispatcher = StandardTestDispatcher()


    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FeatureHomeViewModel(useCase)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getPopularMovies should update state with popular movies on success`() = runTest(testDispatcher) {
        // Arrange
        val films = mock<List<FilmUi>>()
        val response = NetworkState.Success(films)
        `when`(useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_TOP_POPULAR_MOVIES))
            .thenReturn(response)

        // Act
        viewModel.getPopularMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertEquals(films, state.popularMoviesList)
        verify(useCase).getFilmsCollection(Constants.FILM_COLLECTION_TYPE_TOP_POPULAR_MOVIES)
        verifyNoMoreInteractions(useCase)
    }

    @Test
    fun `getPopularMovies should not update state on error`() = runTest(testDispatcher) {
        // Arrange
        val error = Throwable("Network error")
        val response = NetworkState.Error<List<FilmUi>?>(error)
        `when`(useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_TOP_POPULAR_MOVIES))
            .thenReturn(response)

        // Act
        viewModel.getPopularMovies()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertTrue(state.popularMoviesList == null)
        verify(useCase).getFilmsCollection(Constants.FILM_COLLECTION_TYPE_TOP_POPULAR_MOVIES)
        verifyNoMoreInteractions(useCase)
    }

    @Test
    fun `getPopularSeries should update state with popular series on success`() = runTest(testDispatcher) {
        // Arrange
        val series  = mock<List<FilmUi>>()
        val response = NetworkState.Success(series)
        `when`(useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_POPULAR_SERIES))
            .thenReturn(response)

        // Act
        viewModel.getPopularSeries()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertEquals(series, state.popularSeriesList)
        verify(useCase).getFilmsCollection(Constants.FILM_COLLECTION_TYPE_POPULAR_SERIES)
        verifyNoMoreInteractions(useCase)
    }

    @Test
    fun `getCloseReleases should update state with close releases on success`() = runTest(testDispatcher) {
        // Arrange
        val releases  = mock<List<FilmUi>>()
        val response = NetworkState.Success(releases)
        `when`(useCase.getFilmsCollection(Constants.FILM_COLLECTION_TYPE_CLOSES_RELEASES))
            .thenReturn(response)

        // Act
        viewModel.getTop250Movies()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertEquals(releases, state.top250MoviesList)
        verify(useCase).getFilmsCollection(Constants.FILM_COLLECTION_TYPE_CLOSES_RELEASES)
        verifyNoMoreInteractions(useCase)
    }
}