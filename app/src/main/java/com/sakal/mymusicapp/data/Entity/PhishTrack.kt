package com.sakal.mymusicapp.data.Entity

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

data class PhishTrack(
    @SerializedName("title")
    val title: String,
    @SerializedName("artist")
    val artist: String,
    @SerializedName("ava")
    val ava: String,
    @SerializedName("album")
    val album: String


)
