package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

import com.google.gson.annotations.SerializedName

interface DetailMovie {
    data class Base (
        val Title : String,

        val Year : String,

        val Rated: String,

        val Released: String,

        @SerializedName("Runtime") val time: String,

        val Genre: String,

        val Director: String,

        val Actors: String,

        val Plot: String,

        val Poster : String,
    ) : DetailMovie
}