package com.sakal.mymusicapp.data.Entity.Artists

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class AttrArtist(
    @SerializedName("page")
    val page: String,
    @SerializedName("perPage")
    val perPage: String,
    @SerializedName("totalPages")
    val totalPages: String,
    @SerializedName("total")
    val total: String
    ) :Parcelable

