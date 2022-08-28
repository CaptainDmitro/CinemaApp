package ru.captaindmitro.cinemaapp.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.constraintlayout.compose.ExperimentalMotionApi
import androidx.constraintlayout.compose.MotionLayout
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class)
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
    val listState = rememberLazyListState()

    if (movie.isNotEmpty()) {
        movie[0].let {
            LazyColumn(
                state = listState
            ) {
                item {
                    AsyncImage(
                        model = it.poster,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                }
                item {
                    Text(text = it.title)
                    Text(text = it.plot)
                    Text(text = it.actors)
                    Text(text = it.awards)
                    Text(text = it.country)
                    Text(text = it.director)
                    Text(text = it.production)
                }
            }
        }
    }
}
