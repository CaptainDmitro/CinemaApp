package ru.captaindmitro.cinemaapp.ui

import android.util.Log
import androidx.compose.animation.rememberSplineBasedDecay
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import ru.captaindmitro.cinemaapp.ui.common.CinemaAppBar
import ru.captaindmitro.cinemaapp.ui.navigation.Destination
import ru.captaindmitro.cinemaapp.ui.navigation.NavGraph

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
) {
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentScreen = Destination.valueOf(
        backStackEntry?.destination?.route?.substringBefore("/") ?: Destination.Home.name
    )

    val topAppBarState = rememberTopAppBarState()
    val decayAnimationSpec = rememberSplineBasedDecay<Float>()
    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior(
        decayAnimationSpec,
        topAppBarState
    )

    Scaffold(
        topBar = {
            CinemaAppBar(
                currentScreen = currentScreen,
                scrollBehavior = scrollBehavior
            )
        },
        floatingActionButton = {
            // TODO
        }
    ) { innerPadding ->
        NavGraph(
            navHostController = navController,
            modifier = modifier
                .padding(innerPadding)
                .nestedScroll(scrollBehavior.nestedScrollConnection)
        )
    }
}