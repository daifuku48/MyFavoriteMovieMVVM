package com.haritonovdanyluaa.myfavoritemovie.view.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.Repository
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.SearchData
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreEntity
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity
import kotlinx.coroutines.launch

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

    fun getMovieFromApi(name: String) : LiveData<SearchData>
    {
        val result = MutableLiveData<SearchData>()
        viewModelScope.launch {
            val searchData = appRepository.searchMoviesFromApi(name)
            result.postValue(searchData.value)
        }
        return result
    }

}