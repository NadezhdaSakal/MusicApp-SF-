package com.sakal.mymusicapp.domain

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.sakal.mymusicapp.data.Entity.dto.Image
import kotlinx.android.parcel.Parcelize


@Parcelize
data class Audio(
    @SerializedName("name")
    val name: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("image")
    val image: List<Image>,
    var isInFavorites: Boolean = false

) : Parcelable
