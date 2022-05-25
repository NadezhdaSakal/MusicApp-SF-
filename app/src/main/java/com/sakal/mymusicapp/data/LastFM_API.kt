package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.Entity.LastFM_ResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFM_API {
    @GET("3/tracks/popular")
    fun getTracks (
        @Query("api_key") apiKey: String,
        @Query("language") language: String,
        @Query("page") page: Int
    ): Call<LastFM_ResultsDto>
}


