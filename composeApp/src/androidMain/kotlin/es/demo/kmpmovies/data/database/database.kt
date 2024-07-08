package es.demo.kmpmovies.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import es.demo.kmpmovies.data.database.DATABASE_NAME
import es.demo.kmpmovies.data.database.MoviesDatabase

fun getDatabaseBuilder(context: Context): RoomDatabase.Builder<MoviesDatabase> {
    val appContext = context.applicationContext
    val dbFile = appContext.getDatabasePath(DATABASE_NAME)

    return Room.databaseBuilder(
        context = appContext,
        name = dbFile.absolutePath
    )
}