package com.sakal.mymusicapp.data.Entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AttrTrack(
    @SerializedName("track")
    val track: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("image")
    val image: String

) :Parcelable

