package com.sakal.mymusicapp.domain;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Audio(
        var id: Int,
        var ava: String,
        var singer: String,
        var title: String,
        var isInFavorites: Boolean = false

) : Parcelable

