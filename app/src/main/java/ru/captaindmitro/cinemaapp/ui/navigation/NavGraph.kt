package ru.captaindmitro.cinemaapp.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import ru.captaindmitro.cinemaapp.ui.details.DetailsScreen
import ru.captaindmitro.cinemaapp.ui.home.HomeScreen

enum class Destination(val title: String) {
    Home(title = "Movies"),
    Details(title = "Movie details")
}

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Destination.Home.name,
        modifier = modifier
    ) {
        composable(route = Destination.Home.name) {
            HomeScreen(
                navigateToDetails = { movieId: String -> navHostController.navigate("${Destination.Details.name}/$movieId")  }
            )
        }

        composable(
            route = "${Destination.Details.name}/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.StringType })
        ) {
            DetailsScreen(movieId = it.arguments?.getString("movieId") ?: "")
        }
    }
}