package com.sakal.mymusicapp.data.Entity.Artists

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Artists(
    @SerializedName("@attr")
    val attr: AttrArtist,
    @SerializedName("artist")
    val artist: List<Artist>
) : Parcelable