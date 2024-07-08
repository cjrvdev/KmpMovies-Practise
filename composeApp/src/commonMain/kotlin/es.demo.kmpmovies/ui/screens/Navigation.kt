package es.demo.kmpmovies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import es.demo.kmpmovies.BuildConfig
import es.demo.kmpmovies.data.MoviesRepository
import es.demo.kmpmovies.data.MoviesService
import es.demo.kmpmovies.data.database.MoviesDao
import es.demo.kmpmovies.ui.screens.detail.DetailScreen
import es.demo.kmpmovies.ui.screens.home.HomeScreen
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.compose.viewmodel.koinViewModel
import org.koin.core.annotation.KoinExperimentalAPI
import org.koin.core.parameter.parametersOf

@OptIn(KoinExperimentalAPI::class)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onMovieClicked = { movie -> navController.navigate("details/${movie.id}") }
            )
        }
        composable(
            route = "details/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backstackEntry ->
            val movieId = checkNotNull(backstackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                onBack = { navController.popBackStack() },
                vm = koinViewModel(parameters = { parametersOf(movieId) }))

        }
    }
}