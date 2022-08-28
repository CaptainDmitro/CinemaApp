package ru.captaindmitro.cinemaapp.ui.common

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import ru.captaindmitro.cinemaapp.data.MovieData
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.IntOffset
import kotlin.math.roundToInt

enum class SwipeState {
    VISIBLE, MIDDLE
}

@Composable
fun SwipeableCard(
    movie: MovieData,
    onClick: () -> Unit,
) {
    AnimatedVisibility(visible = true) {
        Box(
            modifier = Modifier
                .background(MaterialTheme.colorScheme.primaryContainer, RoundedCornerShape(16.dp))
        ) {
            SwipeableActions(modifier = Modifier.align(Alignment.CenterEnd).fillMaxHeight())
            MovieCard(
                movie = movie,
                onClick = onClick,
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun MovieCard(
    movie: MovieData,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val swipeableState = rememberSwipeableState(
        initialValue = SwipeState.VISIBLE,
    )
    val swipeAnchors = mapOf(0f to SwipeState.VISIBLE, -100f to SwipeState.MIDDLE)
    
    Card(
        onClick = onClick,
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            contentColor = MaterialTheme.colorScheme.onPrimaryContainer
        ),
        modifier = Modifier
            .then(modifier)
            .fillMaxWidth()
            .swipeable(
                state = swipeableState,
                anchors = swipeAnchors,
                thresholds = { _, _ -> FractionalThreshold(0.5f) },
                orientation = Orientation.Horizontal
            )
            .offset{ IntOffset(swipeableState.offset.value.roundToInt(), 0) }
    ) {
        Row {
            AsyncImage(
                model = movie.poster,
                contentDescription = movie.title,
                contentScale = ContentScale.FillHeight,
                modifier = Modifier.heightIn(min = 200.dp, max = 200.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = movie.title,
                    style = MaterialTheme.typography.titleLarge
                )
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .size(1.dp))
                Text(
                    text = movie.plot,
                    overflow = TextOverflow.Ellipsis,
                    maxLines = 3,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    }
}

@Composable
fun SwipeableActions(modifier: Modifier) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
    ) {
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Star,
                contentDescription = null
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Notifications,
                contentDescription = null
            )
        }
        IconButton(onClick = {}) {
            Icon(
                imageVector = Icons.Default.Share,
                contentDescription = null
            )
        }
    }
}