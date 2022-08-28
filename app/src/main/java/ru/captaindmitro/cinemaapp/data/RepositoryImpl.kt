package ru.captaindmitro.cinemaapp.data

import ru.captaindmitro.cinemaapp.data.model.toDomain
import ru.captaindmitro.cinemaapp.domain.model.MovieDomain
import ru.captaindmitro.cinemaapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getRecentMovies(): List<MovieDomain> {
        return remoteDataSource.getRecentMovies().map { it.toDomain() }
    }

    override suspend fun getMovieById(id: String): MovieDomain {
        return remoteDataSource.getMovieById(id).toDomain()
    }
}