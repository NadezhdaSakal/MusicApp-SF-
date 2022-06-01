package com.sakal.mymusicapp.data.Entity.dto

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artists(
    val page: Int,
    val perPage: Int,
    val totalPages: Int,
    val total: Int,
    @SerializedName("artist")
    val artist: List<Artist>
    )