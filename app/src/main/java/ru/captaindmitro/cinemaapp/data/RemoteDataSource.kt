package ru.captaindmitro.cinemaapp.data

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.captaindmitro.cinemaapp.data.api.MovieApi
import ru.captaindmitro.cinemaapp.data.model.MovieData
import javax.inject.Inject

interface RemoteDataSource {
    suspend fun getRecentMovies(): List<MovieData>
    suspend fun getMoviesByKeyword(query: String): List<MovieData>
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

        override suspend fun getMoviesByKeyword(query: String): List<MovieData> = coroutineScope {
            withContext(dispatcher) {
                val res = mutableListOf<MovieData>()
                movieApi.getMoviesByKeyword(query).search.forEach {
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