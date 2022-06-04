package com.sakal.mymusicapp.data.Entity.Tracks

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Track(
    @SerializedName("name")
    val name: String,
    @SerializedName("duration")
    val duration: String,
    @SerializedName("mbid")
    val artist: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("playcount")
    val playcount: String,
    @SerializedName("url")
    val url:String
    ) : Parcelable


