package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.Entity.PhishResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PhishAPI {
    @GET("10/playlists/tracks")
    fun getTracks (
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<PhishResultsDto>
}


