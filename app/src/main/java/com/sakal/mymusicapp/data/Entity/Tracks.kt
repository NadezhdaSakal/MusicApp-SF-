package com.sakal.mymusicapp.data.Entity

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Tracks(
    @SerializedName("@attr")
    val attr: AttrTrack,
    @SerializedName("tracks")
    val track: List<Track>
) : Parcelable

