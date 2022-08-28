package ru.captaindmitro.cinemaapp.ui.details

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.material.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import ru.captaindmitro.cinemaapp.domain.model.MovieDomain
import ru.captaindmitro.cinemaapp.ui.common.UiState

@Composable
fun DetailsScreen(
    movieId: String,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val movie by detailsViewModel.movie.collectAsState()

    LaunchedEffect(Unit) {
        detailsViewModel.getMovieById(movieId)
    }

    when (val state = movie) {
        is UiState.Empty -> {
            Text(text = "No movies found", modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(
                    Alignment.Center
                ))
        }
        is UiState.Loading -> {
            androidx.compose.material3.LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        is UiState.Success -> {
            DetailsScreenContent(state.data)
        }
        is UiState.Error -> {
            Text(text = "${state.exception}", modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(
                    Alignment.Center
                ))
        }
    }
}

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun DetailsScreenContent(
    movie: MovieDomain
) {
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = { },
        frontLayerContent = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Row(
                    horizontalArrangement = SpaceBetween,
                    verticalAlignment = CenterVertically,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Text(
                        text = movie.title,
                        style = MaterialTheme.typography.h6
                    )
                    Text(text = movie.imdbRating)
                }
                Card {
                    Text(
                        text = movie.plot,
                        modifier = Modifier.padding(16.dp)
                    )
                }
                Card {
                    Column(
                        verticalArrangement = Arrangement.spacedBy(16.dp),
                        modifier = Modifier.padding(16.dp)
                    ) {
                        Text(text = "Genre: ${movie.genre}")
                        Text(text = "Director: ${movie.director}")
                        Text(text = "Actors: ${movie.actors}")
                        Text(text = "Country: ${movie.country}")
                        Text(text = "Language: ${movie.language}")
                        Text(text = "Released: ${movie.released}")
                        Text(text = "Awards: ${movie.awards}")
                        Text(text = "Metascore: ${movie.metascore}")
                        Text(text = "Runtime: ${movie.runtime}")
                        Text(text = "Votes: ${movie.imdbVotes}")
                        Text(text = "Rated: ${movie.rated}")
                    }
                }
            }
        },
        backLayerContent = {
            AsyncImage(
                model = movie.poster,
                contentScale = ContentScale.FillWidth,
                contentDescription = "",
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        frontLayerScrimColor = Color.Unspecified,
        backLayerBackgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
        peekHeight = 200.dp
    )
}