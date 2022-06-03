package com.sakal.mymusicapp.data.Entity.dto

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Tracks(
    @SerializedName("@attr")
    val attr: AttrTrack,
    @SerializedName("track")
    val track: List<LastFMTrack>
) : Parcelable

