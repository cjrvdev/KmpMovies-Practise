package es.demo.kmpmovies

import androidx.compose.ui.window.ComposeUIViewController
import data.database.getDatabaseBuilder

fun MainViewController() = ComposeUIViewController {
    val db = getDatabaseBuilder().build()
    App(db.moviesDao())
}