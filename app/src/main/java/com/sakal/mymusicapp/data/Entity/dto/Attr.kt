package com.sakal.mymusicapp.data.Entity.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Attr(
    @SerializedName("page")
    val page: String,
    @SerializedName("perPage")
    val perPage: String,
    @SerializedName("totalPages")
    val totalPages: String,
    @SerializedName("total")
    val total: String
    ) :Parcelable
