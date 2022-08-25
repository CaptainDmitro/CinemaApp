package ru.captaindmitro.cinemaapp.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import ru.captaindmitro.cinemaapp.domain.MovieDomain

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

fun MovieSearchData.toDomain() = MovieDomain(
    title = this.title,
    year = this.year,
    imdbId = this.imdbId,
    type = this.type,
    poster = this.poster
)