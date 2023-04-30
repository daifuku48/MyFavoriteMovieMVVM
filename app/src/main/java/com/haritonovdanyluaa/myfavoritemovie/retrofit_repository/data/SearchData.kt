package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

interface SearchData {
    data class Base(
        val Search : List<Movie>
    ) : SearchData
}