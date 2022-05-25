package com.sakal.mymusicapp.data.Entity

import com.google.gson.annotations.SerializedName

data class JamendoTrack(
    @SerializedName("adult")
    val adult: Boolean,
    @SerializedName("backdrop_path")
    val backdropPath: String,
    @SerializedName("overview")
    val overview: String,
    @SerializedName("ava")
    val avaPath: String,
    @SerializedName("release_date")
    val releaseDate: String,
    @SerializedName("title")
    val title: String

)
