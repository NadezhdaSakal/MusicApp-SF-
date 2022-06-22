package com.sakal.mymusicapp.data.entity

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Tracks(
    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("track")
    val track: List<Track>
) : Parcelable

