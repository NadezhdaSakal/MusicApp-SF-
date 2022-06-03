package com.sakal.mymusicapp.data.Entity.Artists

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ArtistsWrapper(
    @SerializedName("artists")
    val artists: Artists
) : Parcelable