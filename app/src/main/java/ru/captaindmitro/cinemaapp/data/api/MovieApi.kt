package ru.captaindmitro.cinemaapp.data.api

import retrofit2.http.GET
import retrofit2.http.Query
import ru.captaindmitro.cinemaapp.BuildConfig
import ru.captaindmitro.cinemaapp.data.model.MovieData
import ru.captaindmitro.cinemaapp.data.model.ResponseSearch

interface MovieApi {

    @GET("/?apikey=${BuildConfig.API_KEY}&s=all")
    suspend fun getRecentMovies(): ResponseSearch

    @GET("/?apikey=${BuildConfig.API_KEY}")
    suspend fun getMovieById(
        @Query("i") imdbId: String
    ): MovieData

}