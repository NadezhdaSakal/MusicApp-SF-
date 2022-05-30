package com.sakal.mymusicapp.data.Entity

import com.google.gson.annotations.SerializedName

data class PhishResultsDto(
    val success: Boolean,

    val page: Int,
    @SerializedName("data")
    val phishTracks: List<PhishTrack>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_entries")
    val totalEntries: Int
)



