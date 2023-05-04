package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApi {
    @GET("/")
    suspend fun getMovies(
        @Query("s") s: String,
        @Query("apikey") apikey: String
    ): SearchData.Base

    @GET("/")
    suspend fun getDetailMovies(
        @Query("t") t: String,
        @Query("apikey") apikey: String
    ) : List<DetailMovie.Base>
}