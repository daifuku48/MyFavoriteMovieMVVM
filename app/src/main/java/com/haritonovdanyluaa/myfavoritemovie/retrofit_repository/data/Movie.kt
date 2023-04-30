package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

interface Movie {
    data class Base(
        private val Title : String,
        private val Year : String,
        private val Poster : String
    ) : Movie
}