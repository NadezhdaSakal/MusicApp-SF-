package com.sakal.mymusicapp.data.Entity.Tracks

import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import android.os.Parcelable

@Parcelize
data class Tracks(
    @SerializedName("tracks")
    val tracks: List<Track>
) : Parcelable

