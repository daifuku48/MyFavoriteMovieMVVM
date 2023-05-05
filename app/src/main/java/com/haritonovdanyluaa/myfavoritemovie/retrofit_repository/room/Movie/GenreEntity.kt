package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "genre_table")
class GenreEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
     val id: Int,
    @ColumnInfo(name = "name_genre")
     val name: String
)