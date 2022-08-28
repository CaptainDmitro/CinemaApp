package ru.captaindmitro.cinemaapp.ui.home

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.captaindmitro.cinemaapp.ui.common.SwipeableCard
import ru.captaindmitro.cinemaapp.ui.common.UiState

@Composable
fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val movies by homeViewModel.movies.collectAsState()

    when (val state = movies) {
        is UiState.Empty -> {
            Text(text = "No movies found", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
        is UiState.Loading -> {
            LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
        }
        is UiState.Success -> {
            LazyColumn(
                state = lazyListState,
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(items = state.data) { movie ->
                    SwipeableCard(movie = movie, onClick = { navigateToDetails(movie.imdbId) })
                }
            }
        }
        is UiState.Error -> {
            Text(text = "${state.exception}", modifier = Modifier.fillMaxSize().wrapContentSize(Alignment.Center))
        }
    }
}