package com.vitalik123.feature_home

import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.core.utils.NetworkState
import com.vitalik123.feature_home.repository.RepositoryHome
import com.vitalik123.feature_home.use_case.UseCaseHomeImpl
import com.vitalik123.network.dto.FilmCollectionResponse
import com.vitalik123.network.dto.FilmCollectionResponseItem
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
class UseCaseHomeImplTest {

    private lateinit var useCase: UseCaseHomeImpl
    private val repository = mock(RepositoryHome::class.java)
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        useCase = UseCaseHomeImpl(repository)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getFilmsCollection should return Success with mapped data`() =
        runBlocking(testDispatcher) {
            // Arrange
            val filmResponse = listOf(FilmDto(id = 1, title = "Film 1"))
            val expectedUiModel = listOf(filmResponse[0].toFilmUi())
            val itemsMock = mock<List<FilmCollectionResponseItem>>()
            val type = "popular"
            whenever(repository.getFilmsCollections(type)).thenReturn(
                NetworkState.Success(
                    FilmCollectionResponse(
                        total = 100,
                        totalPages = 3,
                        items = itemsMock
                    )
                )
            )

            // Act
            val result = useCase.getFilmsCollection(type)

            // Assert
            assertTrue(result is NetworkState.Success)
            assertEquals(expectedUiModel, (result as NetworkState.Success).data)
            verify(repository).getFilmsCollections(type)
            verifyNoMoreInteractions(repository)
        }

    @Test
    fun `getFilmsCollection should return Error when repository returns Error`() =
        runBlocking(testDispatcher) {
            // Arrange
            val type = "popular"
            val error = Throwable("Network error")
            `when`(repository.getFilmsCollections(type)).thenReturn(NetworkState.Error(error))

            // Act
            val result = useCase.getFilmsCollection(type)

            // Assert
            assertTrue(result is NetworkState.Error)
            assertEquals(error, (result as NetworkState.Error).error)
            verify(repository).getFilmsCollections(type)
            verifyNoMoreInteractions(repository)
        }

    @Test
    fun `getFilmsCollection should return ServerError when repository returns ServerError`() =
        runBlocking(testDispatcher) {
            // Arrange
            val type = "popular"
            val serverError = Throwable(message = "Internal server error")
            `when`(repository.getFilmsCollections(type)).thenReturn(
                NetworkState.ServerError<FilmCollectionResponse>(
                    serverError
                ) as NetworkState<FilmCollectionResponse?>?
            )

            // Act
            val result = useCase.getFilmsCollection(type)

            // Assert
            assertTrue(result is NetworkState.ServerError)
            assertEquals(serverError, (result as NetworkState.ServerError).error)
            verify(repository).getFilmsCollections(type)
            verifyNoMoreInteractions(repository)
        }
}

private data class FilmDto(val id: Long, val title: String) {
    fun toFilmUi(): FilmUi {
        return FilmUi(id = id, title = title, rating = null, poster = "")
    }
}