package ru.captaindmitro.cinemaapp.ui.details

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.captaindmitro.cinemaapp.ui.common.MovieCard

@Composable
fun DetailsScreen(
    movieId: String,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    LaunchedEffect(Unit) {
        detailsViewModel.getMovieById(movieId)
    }

    val movie by detailsViewModel.movie.collectAsState()

    if (movie.isNotEmpty()) {
        movie[0].let {
            Column {
                AsyncImage(model = it.poster, contentDescription = "")
            }
        }
    }
}