package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movie_table")
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "movieId")
     val movieId: Int,
    @ColumnInfo(name = "title")
     val title : String,
    @ColumnInfo(name = "year")
     val year : String,
    @ColumnInfo(name = "genre")
     val genre: String,
    @ColumnInfo(name = "actors")
     val actors: String,
    @ColumnInfo(name = "plot")
     val plot: String,
    @ColumnInfo(name = "poster")
     val poster : String
)
