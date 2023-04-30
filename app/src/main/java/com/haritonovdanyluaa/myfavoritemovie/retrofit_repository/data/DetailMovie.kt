package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

import com.google.gson.annotations.SerializedName

interface DetailMovie {
    data class Base (
        @SerializedName("Title")
        private val title : String,
        @SerializedName("Year")
        private val year : String,
        @SerializedName("Rated")
        private val rated: String,
        @SerializedName("Released")
        private val released: String,
        @SerializedName("Runtime")
        private val time: String,
        @SerializedName("Genre")
        private val genre: String,
        @SerializedName("Director")
        private val director: String,
        @SerializedName("Actors")
        private val actors: String,
        @SerializedName("Plot")
        private val plot: String,
        @SerializedName("Poster")
        private val poster : String,
    ) : DetailMovie
}