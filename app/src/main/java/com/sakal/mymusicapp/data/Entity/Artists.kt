package com.sakal.mymusicapp.data.Entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artists(
    @SerializedName("artists")
    val arists:List<Artist>
) :Parcelable



