package ru.captaindmitro.cinemaapp.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getRecentMovies(): List<MovieData>
    suspend fun getMovieById(id: String): MovieData

    class Base @Inject constructor(
        private val movieApi: MovieApi,
        private val dispatcher: CoroutineDispatcher
    ) : RemoteDataSource {

        override suspend fun getRecentMovies(): List<MovieData> = coroutineScope {
            withContext(dispatcher) {
                val res = mutableListOf<MovieData>()
                movieApi.getRecentMovies().search.forEach {
                    launch { res.add(movieApi.getMovieById(it.imdbId)) }
                }
                res
            }
        }

        override suspend fun getMovieById(id: String): MovieData = withContext(dispatcher) {
            movieApi.getMovieById(id)
        }

    }
}