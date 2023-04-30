package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

interface Movie {
    data class Base(

        val Title : String,

        val Year : String,

        val Poster : String
    ) : Movie
}