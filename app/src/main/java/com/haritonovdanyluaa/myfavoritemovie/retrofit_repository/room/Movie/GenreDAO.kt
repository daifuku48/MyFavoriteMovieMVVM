package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie

import androidx.lifecycle.LiveData
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
    fun getAllGenre() : LiveData<List<GenreEntity>>

    @Query("SELECT * FROM GENRE_TABLE WHERE name_genre = :name")
    fun getGenre(name: String) : LiveData<GenreEntity>
}