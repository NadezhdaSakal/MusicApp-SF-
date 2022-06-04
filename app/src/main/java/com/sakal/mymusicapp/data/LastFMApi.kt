package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.Entity.Artists.*
import com.sakal.mymusicapp.data.Entity.Tracks.*
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface LastFMApi {

    @GET("?method=artist.gettoptracks&artist=louna&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json")
    fun getTracksArtist(
       // @Query("mbid") mbid: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Call<TracksWrapper>

    @GET("?method=artist.getinfo&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json")
    fun getArtistInfo(
        @Query("mbid") id: String
    ): Call<Artist>


    @GET("?method=chart.gettopartists&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json")
    fun getTopArtists(
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Call<ArtistsWrapper>

}


