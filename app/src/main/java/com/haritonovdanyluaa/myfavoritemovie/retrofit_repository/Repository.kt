package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository

import android.app.Application
import androidx.lifecycle.LiveData
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreDAO
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreEntity
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieDAO
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieDatabase
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Repository(application: Application) {

    private var genreDAO : GenreDAO
    private var movieDAO : MovieDAO
    private var genres : LiveData<List<GenreEntity>>
        get() {
            return genreDAO.getAllGenre()
        }
        set(value) {}
    private var movies : LiveData<List<MovieEntity>>? = null
    init{
        val movieDatabase = MovieDatabase.getDatabase(application)
        genreDAO = movieDatabase.genreDao()
        movieDAO = movieDatabase.movieDao()
    }

    fun getGenreMovies(genre: GenreEntity) : LiveData<List<MovieEntity>>
    {
        var movies : LiveData<List<MovieEntity>>? = null
        CoroutineScope(Dispatchers.IO).launch  {
            movies = movieDAO.getAllWithGenre(genre)
        }
        return movies!!
    }

    fun getAllMovies() : LiveData<List<MovieEntity>>
    {
        return movieDAO.getAllMovie()
    }

    fun getAllGenres() : LiveData<List<GenreEntity>>
    {
        return genreDAO.getAllGenre()
    }

    fun insertGenre(genreEntity: GenreEntity)
    {
        CoroutineScope(Dispatchers.IO).launch  {
            genreDAO.insertGenre(genreEntity)
        }
    }

    fun insertMovie(movieEntity: MovieEntity)
    {
        CoroutineScope(Dispatchers.IO).launch{
            movieDAO.insertFavoriteMovie(movieEntity)
        }
    }

    fun deleteMovie(movie: MovieEntity)
    {
        CoroutineScope(Dispatchers.IO).launch{
            movieDAO.deleteFavoriteMovie(movie)
        }
    }

    fun deleteGenre(genre: GenreEntity)
    {
        CoroutineScope(Dispatchers.IO).launch{
            genreDAO.deleteGenre(genre)
        }
    }
}