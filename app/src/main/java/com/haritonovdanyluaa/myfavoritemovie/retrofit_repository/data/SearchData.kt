package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

interface SearchData {
    data class Base(
        private val Search : List<Movie>
    ) : SearchData
}