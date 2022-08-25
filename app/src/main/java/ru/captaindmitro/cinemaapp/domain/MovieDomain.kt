package ru.captaindmitro.cinemaapp.domain

data class MovieDomain(
    val title: String,
    val year: String,
    val imdbId: String,
    val type: String,
    val poster: String
)