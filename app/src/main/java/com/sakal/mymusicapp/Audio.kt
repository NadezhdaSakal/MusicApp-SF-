package com.sakal.mymusicapp;

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Audio(
        var ava: Int,
        var singer: String,
        var title: String,
) : Parcelable

