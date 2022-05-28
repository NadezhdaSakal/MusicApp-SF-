package com.sakal.mymusicapp.data.Entity

import com.google.gson.annotations.SerializedName

data class PhishResultsDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val phishTracks: List<PhishTrack>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)



