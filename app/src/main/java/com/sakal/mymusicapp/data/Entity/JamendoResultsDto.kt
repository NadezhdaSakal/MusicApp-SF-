package com.sakal.mymusicapp.data.Entity

import com.google.gson.annotations.SerializedName

data class JamendoResultsDto(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val JamendoTracks: List<JamendoTrack>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)



