package ru.captaindmitro.cinemaapp.data

import retrofit2.http.GET
import retrofit2.http.Query
import ru.captaindmitro.cinemaapp.BuildConfig

interface MovieApi {

    @GET("/?apikey=${BuildConfig.API_KEY}&s=all")
    suspend fun getRecentMovies(): ResponseSearch

    @GET("/?apikey=${BuildConfig.API_KEY}")
    suspend fun getMovieById(
        @Query("i") imdbId: String
    ): MovieData

}