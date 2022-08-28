package ru.captaindmitro.cinemaapp.domain.common

sealed class Result<out T> {

    class Success<T>(val data: T) : Result<T>()
    class Failure(val error: Throwable) : Result<Nothing>()

}