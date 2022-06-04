package com.sakal.mymusicapp.data.Entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    @SerializedName("artist")
    val artist: Artist,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("image")
    val image: List<Image>,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("mbid")
    val mbid: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("playcount")
    val playcount: String,
    @SerializedName("streamable")
    val streamable: Streamable,
    @SerializedName("url")
    val url: String

) : Parcelable


