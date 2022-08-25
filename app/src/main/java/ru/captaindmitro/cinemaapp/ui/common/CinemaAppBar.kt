package ru.captaindmitro.cinemaapp.ui.common

import android.util.Log
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.twotone.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.unit.dp
import ru.captaindmitro.cinemaapp.ui.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaAppBar(
    currentScreen: Destination,
    scrollBehavior: TopAppBarScrollBehavior,
    modifier: Modifier = Modifier
) {
    var searchExpanded by remember { mutableStateOf(false) }

    if (currentScreen == Destination.Details) {
        scrollBehavior.state.heightOffset = 0f
    }

    SmallTopAppBar(
        title = { Text(text = currentScreen.title) },
        actions = {
            IconButton(onClick = { searchExpanded = !searchExpanded }) {
                Icon(
                    imageVector = if (searchExpanded) Icons.Default.Clear else Icons.Default.Search,
                    contentDescription = ""
                )
            }
        },
        colors = TopAppBarDefaults.smallTopAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        scrollBehavior = scrollBehavior,
        modifier = modifier
    )

    if (searchExpanded) {
        Box(
            contentAlignment = Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 78.dp, start = 16.dp, end = 16.dp)
        ) {
            OutlinedTextField(
                value = "",
                onValueChange = {},
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}