package ru.captaindmitro.cinemaapp.ui.common

sealed class UiState<out T> {
    object Empty : UiState<Nothing>()
    object Loading : UiState<Nothing>()
    class Success<T>(val data: T) : UiState<T>()
    class Error(val exception: Throwable) : UiState<Nothing>()
}