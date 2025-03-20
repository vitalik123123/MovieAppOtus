package com.vitalik123.feature_full_list.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vitalik123.core.dto.ui.FilmUi
import com.vitalik123.network.api.MovieApi
import com.vitalik123.network.mapper.toFilmUi
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import retrofit2.HttpException

class PagingMoviesTopPageSource @AssistedInject constructor(
    private val movieApi: MovieApi,
    @Assisted("type") private val type: String
) : PagingSource<Int, FilmUi>() {

    override fun getRefreshKey(state: PagingState<Int, FilmUi>): Int? {
        val anchorPosition = state.anchorPosition ?: return null
        val page = state.closestPageToPosition(anchorPosition) ?: return null
        return page.prevKey?.plus(1) ?: page.nextKey?.minus(1)
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, FilmUi> {

        try {
            val page: Int = params.key ?: 1
            val response = movieApi.getTopFilms(type = type, page = page)

            return if (response.isSuccessful) {
                val filmList = checkNotNull(response.body()).items
                val nextKey = if (page <= response.body()?.totalPages!!) page + 1 else null
                val prevKey = if (page == 1) null else page - 1
                LoadResult.Page(filmList.map { it.toFilmUi() }, prevKey, nextKey)
            } else {
                LoadResult.Error(HttpException(response))
            }
        } catch (e: HttpException) {
            return LoadResult.Error(e)
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }

    @AssistedFactory
    interface Factory {
        fun create(@Assisted("type") type: String): PagingMoviesTopPageSource
    }
}