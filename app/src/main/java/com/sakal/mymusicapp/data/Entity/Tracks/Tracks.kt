package com.sakal.mymusicapp.data.Entity.Tracks

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Tracks(
    @SerializedName ("toptrack artist")
    val toptrack_artist:String,
    @SerializedName("@attr")
    val attr: AttrTrack,
    @SerializedName("track")
    val track: List<Track>
) : Parcelable

