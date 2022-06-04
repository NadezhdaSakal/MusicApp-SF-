package com.sakal.mymusicapp.data.Entity.Tracks

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import kotlinx.android.parcel.RawValue

@Parcelize
data class Tracks(
    @SerializedName ("toptrack")
    val toptrack_artist:String,
    @SerializedName("@attr")
    val attr: @RawValue AttrTrack,
    @SerializedName("track")
    val track: List<Track>
) : Parcelable

