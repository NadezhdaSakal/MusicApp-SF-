package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.entity.TracksWrapper
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LastFMApi {

    @GET("?method=chart.gettoptracks&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json")
    fun getTracks(
        @Path("artist") artist: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Call<TracksWrapper>
}


