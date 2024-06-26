package es.demo.kmpmovies

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil3.ImageLoader
import coil3.compose.setSingletonImageLoaderFactory
import coil3.request.crossfade
import coil3.util.DebugLogger
import es.demo.kmpmovies.ui.screens.Navigation
import es.demo.kmpmovies.ui.screens.detail.DetailScreen
import es.demo.kmpmovies.ui.screens.home.HomeScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    setSingletonImageLoaderFactory { context ->
        ImageLoader.Builder(context).crossfade(true).logger(DebugLogger()).build()
    }
    Navigation()
}


