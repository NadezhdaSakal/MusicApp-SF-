package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.entity.TracksWrapper
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LastFMApi {

    @GET("?method=chart.gettoptracks&format=json")
    fun getTracks(
        @Path("artist") artist: String,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Observable<TracksWrapper>

    @GET("?method=artist.gettoptracks&format=json")
    fun getTracksFromSearch(
        @Path("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): Observable<TracksWrapper>
}




