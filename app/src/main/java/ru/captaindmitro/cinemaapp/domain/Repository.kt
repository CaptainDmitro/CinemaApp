package ru.captaindmitro.cinemaapp.domain

import ru.captaindmitro.cinemaapp.domain.model.MovieDomain

interface Repository {
    suspend fun getRecentMovies(): List<MovieDomain>
    suspend fun getMovieById(id: String): MovieDomain
}