package es.demo.kmpmovies.ui.screens.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import es.demo.kmpmovies.Movie
import es.demo.kmpmovies.movies
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel : ViewModel() {

    var state by mutableStateOf(UiState())
        private set

    init {
        viewModelScope.launch {
            state = UiState(loading = true)
            delay(2000)
            state = UiState(loading = false, movies)
        }
    }


    data class UiState(
        val loading: Boolean = false,
        val movies: List<Movie> = emptyList()
    )
}