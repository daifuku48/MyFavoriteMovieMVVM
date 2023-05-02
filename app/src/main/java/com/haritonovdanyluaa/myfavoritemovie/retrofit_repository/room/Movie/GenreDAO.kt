package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GenreDAO {
    @Insert
    suspend fun insertGenre(genre:GenreEntity)

    @Delete
    suspend fun deleteGenre(genre: GenreEntity)

    @Query("SELECT * FROM GENRE_TABLE")
    suspend fun getAllGenre() : List<GenreEntity>

    @Query("SELECT * FROM GENRE_TABLE WHERE genreId = :id")
    suspend fun getGenre(id: Int) : GenreEntity
}