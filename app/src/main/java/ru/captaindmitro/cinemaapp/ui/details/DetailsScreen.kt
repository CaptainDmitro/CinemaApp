package ru.captaindmitro.cinemaapp.ui.details

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement.Absolute.SpaceBetween
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage

@OptIn(ExperimentalFoundationApi::class, ExperimentalMaterialApi::class)
@Composable
fun DetailsScreen(
    movieId: String,
    detailsViewModel: DetailsViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val movie by detailsViewModel.movie.collectAsState()
    val scaffoldState = rememberBackdropScaffoldState(initialValue = BackdropValue.Revealed)

    LaunchedEffect(Unit) {
        detailsViewModel.getMovieById(movieId)
    }

    BackdropScaffold(
        scaffoldState = scaffoldState,
        appBar = { },
        frontLayerContent = {
            if (movie.isNotEmpty()) {
                movie[0].let {
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
                                text = it.title,
                                style = MaterialTheme.typography.h6
                            )
                            Text(text = it.imdbRating)
                        }
                        Card {
                            Text(
                                text = it.plot,
                                modifier = Modifier.padding(16.dp)
                            )
                        }
                        Card {
                            Column(
                                verticalArrangement = Arrangement.spacedBy(16.dp),
                                modifier = Modifier.padding(16.dp)
                            ) {
                                Text(text = "Genre: ${it.genre}")
                                Text(text = "Director: ${it.director}")
                                Text(text = "Actors: ${it.actors}")
                                Text(text = "Country: ${it.country}")
                                Text(text = "Language: ${it.language}")
                                Text(text = "Released: ${it.released}")
                                Text(text = "Awards: ${it.awards}")
                                Text(text = "Metascore: ${it.metascore}")
                                Text(text = "Runtime: ${it.runtime}")
                                Text(text = "Votes: ${it.imdbVotes}")
                                Text(text = "Rated: ${it.rated}")
                            }
                        }
                    }
                }
            }
        },
        backLayerContent = {
            if (movie.isNotEmpty()) {
                movie[0].let {
                    AsyncImage(
                        model = it.poster,
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        },
        frontLayerScrimColor = Color.Unspecified,
        backLayerBackgroundColor = androidx.compose.material3.MaterialTheme.colorScheme.primary,
        peekHeight = 200.dp
    )
}