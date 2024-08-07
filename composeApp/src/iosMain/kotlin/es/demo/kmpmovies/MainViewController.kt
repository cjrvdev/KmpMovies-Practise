package es.demo.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import es.demo.kmpmovies.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}