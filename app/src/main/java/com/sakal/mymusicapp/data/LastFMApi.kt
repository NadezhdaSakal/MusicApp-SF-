package com.sakal.mymusicapp.data

import retrofit2.http.GET
import retrofit2.http.Query
import com.sakal.mymusicapp.data.Entity.dto.*
import retrofit2.Call

interface LastFMApi {

    @GET("?method=artist.gettrack&artist=cher&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json")
    fun getTracksArtist(
        @Query("artist") artist: String,
        @Query("page") page: Int
    ): Call<Results>

    @GET("?method=chart.gettopartists&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json")
    fun getTopArtists(
        @Query("limit") limit: Int,
        @Query("page") page: Int,
    ): Call<ArtistsWrapper>

}


