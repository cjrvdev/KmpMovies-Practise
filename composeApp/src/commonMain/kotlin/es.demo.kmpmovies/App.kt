package es.demo.kmpmovies

import androidx.compose.runtime.Composable
import coil3.ImageLoader
import coil3.annotation.ExperimentalCoilApi
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import es.demo.kmpmovies.data.database.MoviesDao
import es.demo.kmpmovies.ui.screens.Navigation
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.compose.KoinContext

@OptIn(ExperimentalCoilApi::class)
@Composable
@Preview
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
    }
    KoinContext {
        Navigation()
    }
}


