package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity(tableName = "movie_table", foreignKeys = [ForeignKey(entity = GenreEntity::class, parentColumns = ["id"], childColumns = ["genreId"], onDelete = ForeignKey.CASCADE)])
data class MovieEntity(
    @PrimaryKey(autoGenerate = true)
    private val movieId: Int,
    @ColumnInfo(name = "title")
    private val title : String,
    @ColumnInfo(name = "year")
    private val year : String,
    @ColumnInfo(name = "rated")
    private val rated: String,
    @ColumnInfo(name = "released")
    private val released: String,
    @ColumnInfo(name = "time")
    private val time: String,
    @ColumnInfo(name = "genre")
    private val genre: String,
    @ColumnInfo(name = "genreId")
    private val genreId: String,
    @ColumnInfo(name = "director")
    private val director: String,
    @ColumnInfo(name = "actors")
    private val actors: String,
    @ColumnInfo(name = "plot")
    private val plot: String,
    @ColumnInfo(name = "poster")
    private val poster : String
)
