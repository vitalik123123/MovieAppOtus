package com.vitalik123.feature_details

import com.vitalik123.core.utils.NetworkState
import com.vitalik123.feature_details.repository.RepositoryDetails
import com.vitalik123.feature_details.use_case.UseCaseDetailsImpl
import com.vitalik123.network.dto.FilmDetailsResponse
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.kotlin.verify
import org.mockito.kotlin.verifyNoMoreInteractions
import org.mockito.kotlin.whenever

@ExperimentalCoroutinesApi
class UseCaseDetailsImplTest {

    private lateinit var useCase: UseCaseDetailsImpl
    private val repository = mock(RepositoryDetails::class.java)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCase = UseCaseDetailsImpl(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFilmsCollection should return Success with mapped data`() =
        runBlocking(testDispatcher) {
            // Arrange
            val filmMock = mock<FilmDetailsResponse>()
            val id = 1L
            whenever(repository.getFilmDetails(id)).thenReturn(
                NetworkState.Success(filmMock)
            )

            // Act
            val result = useCase.getFilmDetails(id)
            testDispatcher.scheduler.advanceUntilIdle()

            // Assert
            assertTrue(result is NetworkState.Success)
            assertEquals(filmMock, (result as NetworkState.Success).data)
            verify(repository).getFilmDetails(id)
            verifyNoMoreInteractions(repository)
        }

    @Test
    fun `getFilmsCollection should return Error when repository returns Error`() =
        runBlocking(testDispatcher) {
            // Arrange
            val id = 1L
            val error = Throwable("Network error")
            `when`(repository.getFilmDetails(id)).thenReturn(NetworkState.Error(error))

            // Act
            val result = useCase.getFilmDetails(id)

            // Assert
            assertTrue(result is NetworkState.Error)
            assertEquals(error, (result as NetworkState.Error).error)
            verify(repository).getFilmDetails(id)
            verifyNoMoreInteractions(repository)
        }

    @Test
    fun `getFilmsCollection should return ServerError when repository returns ServerError`() =
        runBlocking(testDispatcher) {
            // Arrange
            val id = 1L
            val serverError = Throwable(message = "Internal server error")
            `when`(repository.getFilmDetails(id)).thenReturn(
                NetworkState.ServerError<FilmDetailsResponse>(
                    serverError
                ) as NetworkState<FilmDetailsResponse?>?
            )

            // Act
            val result = useCase.getFilmDetails(id)

            // Assert
            assertTrue(result is NetworkState.ServerError)
            assertEquals(serverError, (result as NetworkState.ServerError).error)
            verify(repository).getFilmDetails(id)
            verifyNoMoreInteractions(repository)
        }
}