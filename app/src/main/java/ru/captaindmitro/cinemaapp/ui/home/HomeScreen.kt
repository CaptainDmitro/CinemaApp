package ru.captaindmitro.cinemaapp.ui.home

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import ru.captaindmitro.cinemaapp.ui.common.MovieCard
import ru.captaindmitro.cinemaapp.ui.common.SwipeableCard

@Composable
fun HomeScreen(
    navigateToDetails: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
    modifier: Modifier = Modifier
) {
    val lazyListState = rememberLazyListState()
    val movies by homeViewModel.movies.collectAsState()

    if (movies.isEmpty()) {
        LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
    }

    LazyColumn(
        state = lazyListState,
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)

    ) {
        items(items = movies) { movie ->
            SwipeableCard(movie = movie, onClick = { navigateToDetails(movie.imdbId) })
        }
    }
}