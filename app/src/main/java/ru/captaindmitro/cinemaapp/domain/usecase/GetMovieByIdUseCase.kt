package ru.captaindmitro.cinemaapp.domain.usecase

import ru.captaindmitro.cinemaapp.domain.model.MovieDomain
import ru.captaindmitro.cinemaapp.domain.Repository
import ru.captaindmitro.cinemaapp.domain.common.Result
import javax.inject.Inject

class GetMovieByIdUseCase @Inject constructor(
    private val repository: Repository
) {

    suspend operator fun invoke(id: String): Result<MovieDomain> {
        return try {
            Result.Success(repository.getMovieById(id))
        } catch (exception: Throwable) {
            Result.Failure(exception)
        }
    }

}