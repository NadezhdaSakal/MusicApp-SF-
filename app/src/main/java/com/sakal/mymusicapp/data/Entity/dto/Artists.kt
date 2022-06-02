package com.sakal.mymusicapp.data.Entity.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artists(
    @SerializedName("@attr")
    val attr: Attr,
    @SerializedName("artist")
    val artist: List<Artist>
    ) :Parcelable