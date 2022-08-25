package ru.captaindmitro.cinemaapp.ui.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.captaindmitro.cinemaapp.data.MovieData
import ru.captaindmitro.cinemaapp.domain.MovieDomain
import ru.captaindmitro.cinemaapp.domain.Repository
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository
) : ViewModel() {

    private val _movies: MutableStateFlow<List<MovieData>> = MutableStateFlow(emptyList())
    val movies = _movies.asStateFlow()

    private val _movie: MutableStateFlow<MovieData?> = MutableStateFlow(null)
    val movie = _movie.asStateFlow()

    init {
        Log.i("Main", "HomeViewModel created")
        viewModelScope.launch {
            _movies.value = repository.getRecentMovies()
        }
        viewModelScope.launch {
            val res = repository.getMovieById("tt7395114")
            Log.i("Main", "Received movie: $res")
        }
    }

}