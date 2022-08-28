package ru.captaindmitro.cinemaapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.captaindmitro.cinemaapp.domain.model.MovieDomain
import ru.captaindmitro.cinemaapp.domain.common.Result
import ru.captaindmitro.cinemaapp.domain.usecase.GetRecentMoviesUseCase
import ru.captaindmitro.cinemaapp.ui.common.UiState
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getRecentMoviesUseCase: GetRecentMoviesUseCase
) : ViewModel() {

    private val _movies: MutableStateFlow<UiState<List<MovieDomain>>> = MutableStateFlow(UiState.Empty)
    val movies = _movies.asStateFlow()

    init {
        viewModelScope.launch {
            _movies.value = UiState.Loading
            when (val result = getRecentMoviesUseCase.invoke()) {
                is Result.Success -> {
                    _movies.value = if (result.data.isEmpty()) UiState.Empty else UiState.Success(result.data)
                }
                is Result.Failure -> {
                    _movies.value = UiState.Error(result.error)
                }
            }
        }
    }

}