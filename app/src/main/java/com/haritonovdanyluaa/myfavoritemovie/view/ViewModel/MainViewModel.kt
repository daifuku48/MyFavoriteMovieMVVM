package com.haritonovdanyluaa.myfavoritemovie.view.ViewModel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.Repository
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.Movie
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.SearchData
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreEntity
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private var application: Application) : AndroidViewModel(application) {
    private var appRepository : Repository = Repository(application)
    private var movies : LiveData<List<MovieEntity>> = appRepository.getAllMovies()
    private var genres : LiveData<List<GenreEntity>> = appRepository.getAllGenres()
    val movieList = MutableLiveData<List<Movie>>()
    val loading = MutableLiveData<Boolean>()
    var job: Job? = null
    fun getMovieByGenre(genre: GenreEntity) : LiveData<List<MovieEntity>>
    {
        return appRepository.getGenreMovies(genre)
    }
    fun getMovieFromApi(name: String) {
        job = CoroutineScope(Dispatchers.IO).launch {
            val response = appRepository.searchMoviesFromApi(name)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body()?.Search)
                    loading.value = false
                }
            }
        }
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