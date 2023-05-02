package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase


@Database(entities = [MovieEntity::class, GenreEntity::class], version = 1)
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
                    .addCallback(object : RoomDatabase.Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            // do something when database is created
                        }

                        override fun onOpen(db: SupportSQLiteDatabase) {
                            super.onOpen(db)
                            // do something when database is opened
                        }
                    })
                    .build()
                Instance = instance

                instance
            }
        }
    }
}