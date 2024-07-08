package es.demo.kmpmovies.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
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
import es.demo.kmpmovies.ui.screens.detail.DetailViewModel
import es.demo.kmpmovies.ui.screens.home.HomeScreen
import es.demo.kmpmovies.ui.screens.home.HomeViewModel
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.URLProtocol
import io.ktor.serialization.kotlinx.json.json
import kmpmovies.composeapp.generated.resources.Res
import kmpmovies.composeapp.generated.resources.api_key
import kotlinx.serialization.json.Json
import org.jetbrains.compose.resources.stringResource

@Composable
fun Navigation(moviesDao: MoviesDao) {
    val navController = rememberNavController()
    val moviesRepository = rememberMoviesRepository(moviesDao)

    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(
                onMovieClicked = { movie -> navController.navigate("details/${movie.id}") },
                vm = HomeViewModel(moviesRepository = moviesRepository)
            )
        }
        composable(
            route = "details/{movieId}",
            arguments = listOf(navArgument("movieId") { type = NavType.IntType })
        ) { backstackEntry ->
            val movieId = checkNotNull(backstackEntry.arguments?.getInt("movieId"))
            DetailScreen(
                vm = viewModel { DetailViewModel(movieId, moviesRepository) },
                onBack = { navController.popBackStack() })
        }
    }
}

@Composable
private fun rememberMoviesRepository(
    moviesDao: MoviesDao
): MoviesRepository =
    remember {
        val client = HttpClient {
            install(ContentNegotiation) {
                json(json = Json {
                    ignoreUnknownKeys = true
                })
            }
            install(DefaultRequest) {
                url {
                    protocol = URLProtocol.HTTPS
                    host = "api.themoviedb.org"
                    parameters.append("api_key", BuildConfig.API_KEY)
                }
            }
        }

        MoviesRepository(MoviesService(client), moviesDao)
    }