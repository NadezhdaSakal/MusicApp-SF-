package com.sakal.mymusicapp.data.Entity.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TracksWrapper(
    @SerializedName("Tracks")
    val Tracks: Tracks
) : Parcelable

