package ru.captaindmitro.cinemaapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.captaindmitro.cinemaapp.domain.model.MovieDomain
import ru.captaindmitro.cinemaapp.domain.common.Result
import ru.captaindmitro.cinemaapp.domain.usecase.GetMovieByIdUseCase
import ru.captaindmitro.cinemaapp.ui.common.UiState
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getMovieByIdUseCase: GetMovieByIdUseCase
) : ViewModel() {

    private val _movie: MutableStateFlow<UiState<MovieDomain>> = MutableStateFlow(UiState.Empty)
    val movie = _movie.asStateFlow()

    fun getMovieById(id: String) {
        _movie.value = UiState.Loading
        viewModelScope.launch {
            when (val result = getMovieByIdUseCase.invoke(id)) {
                is Result.Success -> {
                    _movie.value = UiState.Success(result.data)
                }
                is Result.Failure -> {
                    _movie.value = UiState.Error(result.error)
                }
            }
        }
    }
}