package com.sakal.mymusicapp.data.Entity

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable
import com.sakal.mymusicapp.data.Entity.Track

@Parcelize
data class Tracks(
    @SerializedName("@attr")
    val attr: AttrTrack,
    @SerializedName("tracks")
    val tracks: List<Track>
) : Parcelable

