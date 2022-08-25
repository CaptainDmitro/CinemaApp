package ru.captaindmitro.cinemaapp.domain

import ru.captaindmitro.cinemaapp.data.MovieData

interface Repository {
    suspend fun getRecentMovies(): List<MovieData>
    suspend fun getMovieById(id: String): MovieData
}