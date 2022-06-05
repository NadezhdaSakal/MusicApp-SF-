package com.sakal.mymusicapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sakal.mymusicapp.data.Entity.Artist
import com.sakal.mymusicapp.data.Entity.Image
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Audio(
    @SerializedName("track")
    val track: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("image")
    val image: String,
    var isInFavorites: Boolean = false

) : Parcelable
