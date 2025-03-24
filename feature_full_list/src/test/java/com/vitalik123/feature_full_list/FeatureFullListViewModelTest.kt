package com.vitalik123.feature_full_list


import com.vitalik123.database.entity.WatchlistEntity
import com.vitalik123.database.repository.ratelist.RatelistRoomRepository
import com.vitalik123.database.repository.watchlist.WatchlistRoomRepository
import com.vitalik123.feature_full_list.repository.PagingMoviesTopPageSource
import com.vitalik123.feature_full_list.view_model.FeatureFullListViewModel
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
class FeatureFullListViewModelTest {

    private lateinit var viewModel: FeatureFullListViewModel
    private val watchlistRoomRepository = mock(WatchlistRoomRepository::class.java)
    private val ratelistRoomRepository = mock(RatelistRoomRepository::class.java)
    private val pagingSourceFactory = mock(PagingMoviesTopPageSource.Factory::class.java)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FeatureFullListViewModel(
            watchlistRoomRepository,
            ratelistRoomRepository,
            pagingSourceFactory
        )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `setType should update type StateFlow`() = runTest(testDispatcher) {
        // Arrange
        val newType = "popular"

        // Act
        viewModel.setType(newType)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(newType, viewModel.type.value)
    }

    @Test
    fun `define with isWatchlist true should fetch all from watchlist`() = runTest(testDispatcher) {
        // Arrange
        val watchlistItems = mock<List<WatchlistEntity>>()
        `when`(watchlistRoomRepository.getAllWatchlist()).thenReturn(watchlistItems)

        // Act
        viewModel.define(isWatchlist = true, isRatelist = false, type = "")
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertEquals(watchlistItems, state.filmsFromWatchlist)
        verify(watchlistRoomRepository).getAllWatchlist()
        verifyNoMoreInteractions(watchlistRoomRepository)
    }

    @Test
    fun `define with isWatchlist false should set type`() = runTest(testDispatcher) {
        // Arrange
        val newType = "top_rated"

        // Act
        viewModel.define(isWatchlist = false, isRatelist = false, type = newType)
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        assertEquals(newType, viewModel.type.value)
    }

    @Test
    fun `deleteAllWatchlist should clear watchlist and update state`() = runTest(testDispatcher) {
        // Arrange
        val initialWatchlist = mock<List<WatchlistEntity>>()
        `when`(watchlistRoomRepository.getAllWatchlist()).thenReturn(initialWatchlist)

        // Act
        viewModel.getAllFromWatchlist()
        testDispatcher.scheduler.advanceUntilIdle()

        viewModel.deleteAllWatchlist()
        testDispatcher.scheduler.advanceUntilIdle()

        // Assert
        val state = viewModel.state.value
        assertTrue(state.filmsFromWatchlist.isEmpty())
    }
}