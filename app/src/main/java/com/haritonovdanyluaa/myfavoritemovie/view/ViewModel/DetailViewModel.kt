package com.haritonovdanyluaa.myfavoritemovie.view.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.ColumnInfo
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.Repository
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.DetailMovie
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.SearchData
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreEntity
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class DetailViewModel(private var application: Application) : AndroidViewModel(application) {
    private var appRepository : Repository = Repository(application)

    private var _detailMovie = MutableLiveData<DetailMovie>()
    val detailMovie : LiveData<DetailMovie>
        get() = _detailMovie

    var jobDetailMovie: Job? = null

    fun getDetailMovieFromApi(name: String) {
        jobDetailMovie = CoroutineScope(Dispatchers.IO).launch {
            val response = appRepository.getDetailMovie(name)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    _detailMovie.postValue(response.body())
                }
            }
        }
    }

    fun insertMovie()
    {
        val movieEntity = MovieEntity(0, detailMovie.value?.title.toString(),
        detailMovie.value?.year.toString(),
        detailMovie.value?.genre.toString(),
        0,
        detailMovie.value?.actors.toString(),
        detailMovie.value?.plot.toString(),
        detailMovie.value?.poster.toString())
        appRepository.insertMovie(movieEntity)
    }
}