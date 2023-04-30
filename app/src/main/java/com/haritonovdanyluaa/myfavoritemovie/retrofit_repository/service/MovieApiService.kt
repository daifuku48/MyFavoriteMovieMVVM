package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.service

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

interface MovieApiService {

    fun<T> service(clasz : Class<T>): T

    class Base : MovieApiService {
        override fun <T> service(clasz: Class<T>) : T {
            val interceptor = HttpLoggingInterceptor().apply {
                setLevel(HttpLoggingInterceptor.Level.BODY)
            }
            val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build()
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return retrofit.create(clasz)
        }

        companion object {
            private const val BASE_URL = "http://www.omdbapi.com/"
        }
    }
}