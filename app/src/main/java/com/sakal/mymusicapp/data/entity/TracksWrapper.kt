package com.sakal.mymusicapp.data.entity

import com.google.gson.annotations.SerializedName
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TracksWrapper(
    @SerializedName("tracks")
    val tracks: Tracks

) : Parcelable
