package ru.captaindmitro.cinemaapp.data.model

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseSearch(
    @Json(name = "Search") val search: List<MovieSearchData>,
    @Json(name = "totalResults") val totalResults: Int,
    @Json(name = "Response") val response: String
)

@JsonClass(generateAdapter = true)
data class MovieSearchData(
    @Json(name = "Title") val title: String,
    @Json(name = "Year") val year: String,
    @Json(name = "imdbID") val imdbId: String,
    @Json(name = "Type") val type: String,
    @Json(name = "Poster") val poster: String
)