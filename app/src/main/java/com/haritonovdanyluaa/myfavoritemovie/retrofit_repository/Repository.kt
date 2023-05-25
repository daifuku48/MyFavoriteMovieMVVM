package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.DetailMovie
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.MovieApi
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.SearchData
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreEntity
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieDAO
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieDatabase
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(application: Application) {

    private lateinit  var movieApi : MovieApi
    private var movieDAO : MovieDAO

    init{
        val movieDatabase = MovieDatabase.getDatabase(application)
        movieDAO = movieDatabase.movieDao()
        configureRetrofit()
    }

    private fun configureRetrofit()
    {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val httpClient = OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://www.omdbapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()

        movieApi = retrofit.create(MovieApi::class.java)
    }

    suspend fun getAllMovies() : LiveData<List<MovieEntity>>
    {
        var result : LiveData<List<MovieEntity>>
        coroutineScope {
            result = movieDAO.getAllMovie()
        }
        return result
    }

    suspend fun insertMovie(movieEntity: MovieEntity)
    {
        coroutineScope {
            movieDAO.insertFavoriteMovie(movieEntity)
        }
    }

    fun deleteMovie(movie: MovieEntity)
    {
        CoroutineScope(Dispatchers.IO).launch{
            movieDAO.deleteFavoriteMovie(movie)
        }
    }

    suspend fun searchMoviesFromApi(name: String) : Response<SearchData>
    {
        var result : Response<SearchData>
        coroutineScope {
            result = movieApi.getMovies(name, "244031db")
        }
        return result
    }

    suspend fun getDetailMovie(name: String) : Response<DetailMovie> {
        var result : Response<DetailMovie>
        coroutineScope {
            result = movieApi.getDetailMovies(name, "244031db")
        }
        return result
    }
}