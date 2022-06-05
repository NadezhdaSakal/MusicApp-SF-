package com.sakal.mymusicapp.data.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Streamable(
    @SerializedName("#text")
    val text: String,
    @SerializedName("fulltrack")
    val fulltrack: String

) : Parcelable
