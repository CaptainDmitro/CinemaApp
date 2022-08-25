package ru.captaindmitro.cinemaapp.data

import ru.captaindmitro.cinemaapp.domain.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource
) : Repository {
    override suspend fun getRecentMovies(): List<MovieData> {
        return remoteDataSource.getRecentMovies()
    }

    override suspend fun getMovieById(id: String): MovieData {
        return remoteDataSource.getMovieById(id)
    }
}