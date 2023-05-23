package com.haritonovdanyluaa.myfavoritemovie.retrofit_repository.data

import com.google.gson.annotations.SerializedName

    data class DetailMovie (
        @SerializedName("Title")
        val title : String,
        @SerializedName("Year")
        val year : String,
        @SerializedName("Rated")
        val rated: String,
        @SerializedName("Released")
        val released: String,
        @SerializedName("Runtime")
        val time: String,
        @SerializedName("Genre")
        val genre: String,
        @SerializedName("Director")
        val director: String,
        @SerializedName("Actors")
        val actors: String,
        @SerializedName("Plot")
        val plot: String,
        @SerializedName("Poster")
        val poster : String)