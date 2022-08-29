package ru.captaindmitro.cinemaapp.ui.common

import androidx.compose.animation.*
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.focusTarget
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import ru.captaindmitro.cinemaapp.ui.navigation.Destination

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CinemaAppBar(
    currentScreen: Destination,
    scrollBehavior: TopAppBarScrollBehavior,
    onSearch: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var searchExpanded by remember { mutableStateOf(false) }

    if (currentScreen != Destination.Details) {
        SmallTopAppBar(
            title = { Text(text = currentScreen.title) },
            actions = {
                IconButton(onClick = { searchExpanded = !searchExpanded }) {
                    Icon(
                        imageVector = if (searchExpanded) Icons.Outlined.Clear else Icons.Outlined.Search,
                        tint = Color.White,
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
    }

    AnimatedVisibility(
        visible = searchExpanded,
        enter = slideInVertically() + fadeIn(),
        exit = slideOutVertically() + fadeOut()
    ) {
        var text by remember { mutableStateOf("") }

        Box(
            contentAlignment = Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 78.dp, start = 16.dp, end = 16.dp)
        ) {
            OutlinedTextField(
                value = text,
                onValueChange = { newText -> text = newText },
                singleLine = true,
                keyboardActions = KeyboardActions(
                    onDone = {
                        onSearch(text)
                    }
                ),
                modifier = Modifier
                    .fillMaxWidth()
            )
        }
    }
}