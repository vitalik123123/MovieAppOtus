package com.vitalik123.feature_details

import com.vitalik123.core.dto.ui.FilmDetailsUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepository
import com.vitalik123.feature_details.use_case.UseCaseDetails
import com.vitalik123.feature_details.view_model.FeatureDetailsViewModel
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

@ExperimentalCoroutinesApi
class FeatureDetailsViewModelTest {

    private lateinit var viewModel: FeatureDetailsViewModel
    private val useCase = mock(UseCaseDetails::class.java)
    private val watchlistRoomRepository = mock(WatchlistRoomRepository::class.java)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FeatureDetailsViewModel(useCase, watchlistRoomRepository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFilmDetails should update state with film details from repository`() =
        runTest(testDispatcher) {
            // Arrange
            val filmId = 1L
            val filmDetails = mock<FilmDetailsUi>()
            val response = NetworkState.Success(filmDetails)
            `when`(useCase.getFilmDetails(filmId)).thenReturn(response)

            // Act
            viewModel.getFilmDetails(filmId)
            testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            val state = viewModel.state.value
            assertEquals(filmDetails, state.filmDetails)
            verify(useCase).getFilmDetails(filmId)
            verifyNoMoreInteractions(useCase)
        }

    @Test
    fun `getFilmDetails should update state with film details from watchlist if exists`() =
        runTest(testDispatcher) {
            // Arrange
            val filmId = 1L
            val watchlistFilm = mock<FilmDetailsUi>()
            `when`(watchlistRoomRepository.existsMovieToWatchlist(filmId)).thenReturn(true)
            `when`(watchlistRoomRepository.getMovieFromWatchlist(filmId)).thenReturn(watchlistFilm)

            // Act
            viewModel.getFilmDetails(filmId)
            testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            val state = viewModel.state.value
            assertEquals(watchlistFilm, state.filmDetails)
            assertTrue(state.isSaveWatchlist)
            verify(watchlistRoomRepository).existsMovieToWatchlist(filmId)
            verify(watchlistRoomRepository).getMovieFromWatchlist(filmId)
            verifyNoMoreInteractions(watchlistRoomRepository)
        }

    @Test
    fun `saveFilmToWatchlist should save film and update isSaveWatchlist`() =
        runTest(testDispatcher) {
            // Arrange
            val filmId = 1L
            val filmDetails = mock<FilmDetailsUi>()
            `when`(watchlistRoomRepository.existsMovieToWatchlist(filmId)).thenReturn(true)

            // Act
            viewModel.saveFilmToWatchlist()
            testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            val state = viewModel.state.value
            assertTrue(state.isSaveWatchlist)
            verify(watchlistRoomRepository).saveMovieToWatchlist(filmDetails)
            verify(watchlistRoomRepository).existsMovieToWatchlist(filmId)
            verifyNoMoreInteractions(watchlistRoomRepository)
        }

    @Test
    fun `deleteFilmFromWatchlist should delete film and update isSaveWatchlist`() =
        runTest(testDispatcher) {
            // Arrange
            val filmId = 1L
            `when`(watchlistRoomRepository.existsMovieToWatchlist(filmId)).thenReturn(false)

            // Act
            viewModel.deleteFilmFromWatchlist()
            testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            val state = viewModel.state.value
            assertTrue(!state.isSaveWatchlist)
            verify(watchlistRoomRepository).deleteMovieFromWatchlist(filmId)
            verify(watchlistRoomRepository).existsMovieToWatchlist(filmId)
            verifyNoMoreInteractions(watchlistRoomRepository)
        }

    @Test
    fun `existWatchlist should update isSaveWatchlist based on repository result`() = runTest {
        // Arrange
        val filmId = 1L
        `when`(watchlistRoomRepository.existsMovieToWatchlist(filmId)).thenReturn(true)

        // Act
        viewModel.existWatchlist(filmId)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertTrue(state.isSaveWatchlist)
        verify(watchlistRoomRepository).existsMovieToWatchlist(filmId)
        verifyNoMoreInteractions(watchlistRoomRepository)
    }
}