package ru.captaindmitro.cinemaapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.captaindmitro.cinemaapp.data.MovieData
import ru.captaindmitro.cinemaapp.domain.Repository
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movie: MutableStateFlow<List<MovieData>> = MutableStateFlow(emptyList())
    val movie = _movie.asStateFlow()

    fun getMovieById(id: String) {
        viewModelScope.launch {
            _movie.value = listOf(repository.getMovieById(id))
        }
    }
}