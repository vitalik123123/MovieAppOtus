package com.vitalik123.network.api

import com.vitalik123.network.dto.FilmCollectionResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {

    @GET("api/v2.2/films/top")
    fun getTopFilms(
        @Query("type") type: String,
        @Query("page") page: Int
    ): Response<FilmCollectionResponse>
}