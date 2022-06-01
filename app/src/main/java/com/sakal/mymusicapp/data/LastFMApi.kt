package com.sakal.mymusicapp.data

import retrofit2.http.GET
import retrofit2.http.Query
import com.sakal.mymusicapp.data.Entity.dto.*
import retrofit2.Call

interface LastFMApi {

    @GET("/2.0/?method=artist.gettrack&artist=cher&api_key=ac0af5806a8796b526f2cff61ad5a29f&format=json")
    fun getTracksArtist(
        @Query("artist") artist: String

    ): Call<Results>

    @GET("/2.0/?method=chart.gettopartists&api_key=ac0af5806a8796b526f2cff61ad5a29f&format=json")
    fun getTopArtists(): Call<Artists>


}


