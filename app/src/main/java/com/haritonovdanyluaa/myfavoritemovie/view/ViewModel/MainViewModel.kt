package com.haritonovdanyluaa.myfavoritemovie.view.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.Repository
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreEntity
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity

class MainViewModel(private var application: Application) : AndroidViewModel(application) {
    private var appRepository : Repository = Repository(application)
    private var movies : LiveData<List<MovieEntity>> = appRepository.getAllMovies()
    private var genres : LiveData<List<GenreEntity>> = appRepository.getAllGenres()

    fun getMovieByGenre(genre: GenreEntity) : LiveData<List<MovieEntity>>
    {
        return appRepository.getGenreMovies(genre)
    }

    fun insertMovie(movieEntity: MovieEntity)
    {
        appRepository.insertMovie(movieEntity)
    }

    fun deleteMovie(movieEntity: MovieEntity)
    {
        appRepository.deleteMovie(movieEntity)
    }




}