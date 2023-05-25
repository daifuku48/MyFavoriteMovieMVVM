package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [MovieEntity::class, GenreEntity::class], version = 10)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao() : MovieDAO
    companion object {

        @Volatile
        private var Instance : MovieDatabase? = null
        fun getDatabase(context: Context) : MovieDatabase{
            return Instance ?: synchronized(this){
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieDatabase::class.java,
                    "movie_db"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                Instance = instance
                instance
            }
        }
    }
}