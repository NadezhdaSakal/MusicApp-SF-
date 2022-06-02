package com.sakal.mymusicapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Audio(
    @SerializedName("name")
    val name: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("image")
    val image: String,
    var isInFavorites: Boolean = false

) : Parcelable
