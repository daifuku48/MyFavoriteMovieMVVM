package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.Movie
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.MovieApi
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data.SearchData
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreDAO
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.GenreEntity
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieDAO
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieDatabase
import com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.room.Movie.MovieEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class Repository(application: Application) {

    private lateinit  var movieApi : MovieApi
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

    fun getGenreMovies(genre: GenreEntity) : LiveData<List<MovieEntity>>
    {
        var movies : LiveData<List<MovieEntity>>? = null
        CoroutineScope(Dispatchers.IO).launch  {
            movies = movieDAO.getAllWithGenre(genre.name)
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

    fun searchMoviesFromApi(name: String) : MutableLiveData<SearchData.Base>
    {
        val myLiveData = MutableLiveData<SearchData.Base>()
        lifecycle.launch{

        }
        val result = movieApi.getMovies(name, "244031db")
        myLiveData.value = result
        Log.d("movie", "movie is searching")
        return myLiveData
    }
}