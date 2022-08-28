package ru.captaindmitro.cinemaapp.domain.usecase

import ru.captaindmitro.cinemaapp.domain.model.MovieDomain
import ru.captaindmitro.cinemaapp.domain.Repository
import ru.captaindmitro.cinemaapp.domain.common.Result
import javax.inject.Inject

class GetRecentMoviesUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(): Result<List<MovieDomain>> {
        return try {
            Result.Success(repository.getRecentMovies())
        } catch (exception: Throwable) {
            Result.Failure(exception)
        }
    }
}