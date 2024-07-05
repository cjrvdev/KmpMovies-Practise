package data.database

import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.driver.bundled.BundledSQLiteDriver
import es.demo.kmpmovies.data.database.DATABASE_NAME
import es.demo.kmpmovies.data.database.MoviesDatabase
import es.demo.kmpmovies.data.database.instantiateImpl
import platform.Foundation.NSHomeDirectory

fun getDatabaseBuilder(): RoomDatabase.Builder<MoviesDatabase> {
    val dbFilePath = NSHomeDirectory() + "/$DATABASE_NAME"

    return Room.databaseBuilder<MoviesDatabase>(
        name = dbFilePath,
        factory = { MoviesDatabase::class.instantiateImpl() })
        .setDriver(BundledSQLiteDriver())
}