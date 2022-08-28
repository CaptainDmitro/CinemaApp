package ru.captaindmitro.cinemaapp.data.model

import ru.captaindmitro.cinemaapp.domain.model.MovieDomain

fun MovieData.toDomain() = MovieDomain(
    title = this.title,
    actors = this.actors,
    awards = this.awards,
    boxOffice = this.boxOffice,
    country = this.country,
    director = this.director,
    dvd = this.dvd,
    genre = this.genre,
    imdbId = this.imdbId,
    imdbRating = this.imdbRating,
    imdbVotes = this.imdbVotes,
    language = this.language,
    metascore = this.metascore,
    plot = this.plot,
    poster = this.poster,
    production = this.production,
    rated = this.rated,
    released = this.released,
    runtime = this.runtime,
    type = this.type,
    website = this.website,
    writer = this.writer,
    year = this.year
)

