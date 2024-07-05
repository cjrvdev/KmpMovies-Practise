package es.demo.kmpmovies.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import es.demo.kmpmovies.data.Movie

// Esto es un HACK por un bug de Room
interface DB {
    fun clearAllTables()
}

const val DATABASE_NAME = "movies.db"

@Database(entities = [Movie::class], version = 1)
abstract class MoviesDatabase : RoomDatabase(), DB {
    abstract fun moviesDao(): MoviesDao

    override fun clearAllTables() {

    }
}