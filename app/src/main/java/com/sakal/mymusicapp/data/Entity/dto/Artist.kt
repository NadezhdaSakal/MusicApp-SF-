package com.sakal.mymusicapp.data.Entity.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artist(
    @SerializedName("name")
    val name: String,
    @SerializedName("mbid")
    val mbid:String,
    @SerializedName("url")
    val url:String,
    @SerializedName("image")
    val image: String,
    @SerializedName("streamable")
    val streamable: Int,
    @SerializedName("listeners")
    val listeners: String,
    @SerializedName("playcount")
    val playcount: String

    ) : Parcelable