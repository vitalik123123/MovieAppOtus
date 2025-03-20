package com.vitalik123.network.api

import com.vitalik123.network.dto.FilmCastResponse
import com.vitalik123.network.dto.FilmCollectionResponse
import com.vitalik123.network.dto.FilmDetailsResponse
import com.vitalik123.network.dto.FilmRelatedResponse
import com.vitalik123.network.dto.FilmSearchResponse
import com.vitalik123.network.dto.FilmSimilarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApi {

    @GET("api/v2.2/films/collections")
    suspend fun getTopFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): Response<FilmCollectionResponse>

    @GET("api/v2.2/films/{id}")
    suspend fun getFilmDetailsById(
        @Path("id") id: Long
    ): Response<FilmDetailsResponse>

    @GET("api/v1/staff")
    suspend fun getFilmCast(
        @Query("filmId") filmId: Long
    ): Response<List<FilmCastResponse>>

    @GET("api/v2.1/films/{id}/sequels_and_prequels")
    suspend fun getFilmSequelsAndPrequels(
        @Path("id") id: Long
    ): Response<List<FilmRelatedResponse>>

    @GET("api/v2.2/films/{id}/similars")
    suspend fun getFilmSimilars(
        @Path("id") id: Long
    ): Response<FilmSimilarResponse>


    @GET("api/v2.1/films/search-by-keyword")
    suspend fun getFilmsSearchByKeyword(
        @Query("keyword") keyword: String,
        @Query("page") page: Int
    ): Response<FilmSearchResponse>
}