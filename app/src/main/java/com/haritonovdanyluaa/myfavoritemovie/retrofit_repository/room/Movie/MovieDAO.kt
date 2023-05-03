package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query


@Dao
interface MovieDAO {

    @Query("SELECT * FROM MOVIE_TABLE WHERE movieId = :id")
    fun getMovie(id: Int) : LiveData<MovieEntity>

    @Insert
    suspend fun insertFavoriteMovie(movie: MovieEntity)

    @Delete
    suspend fun deleteFavoriteMovie(movie: MovieEntity)

    @Query("SELECT * FROM MOVIE_TABLE")
    fun getAllMovie() : LiveData<List<MovieEntity>>

    @Query("SELECT * FROM MOVIE_TABLE WHERE genre = :genre")
    fun getAllWithGenre(genre: GenreEntity) : LiveData<List<MovieEntity>>
}