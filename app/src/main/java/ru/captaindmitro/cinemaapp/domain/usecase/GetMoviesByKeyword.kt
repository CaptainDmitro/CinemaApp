package ru.captaindmitro.cinemaapp.domain.usecase

import ru.captaindmitro.cinemaapp.domain.Repository
import ru.captaindmitro.cinemaapp.domain.common.Result
import ru.captaindmitro.cinemaapp.domain.model.MovieDomain
import javax.inject.Inject

class GetMoviesByKeyword @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(query: String): Result<List<MovieDomain>> {
        return try {
            Result.Success(repository.getMoviesByKeyword(query))
        } catch (exception: Throwable) {
            Result.Failure(exception)
        }
    }
}