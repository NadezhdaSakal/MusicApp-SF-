package com.sakal.mymusicapp.data.Entity

import android.icu.text.CaseMap
import com.google.gson.annotations.SerializedName

data class PhishTrack(
    val id: Int,
    val title: String,
    val duration: Int,
    @SerializedName("show_id")
    val showId: Int,
    @SerializedName("show_date")
    val showDate: String,
    val set: Int,
    val position: Int,
    @SerializedName("likes_count")
    val likesCount: Int,
    val slug: String,
    val mp3: String


)
