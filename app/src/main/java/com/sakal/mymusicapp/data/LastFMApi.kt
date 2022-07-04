package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.BuildConfig
import com.sakal.mymusicapp.data.entity.TracksWrapper
import io.reactivex.rxjava3.core.Observable
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LastFMApi {

    @GET("?method=chart.gettoptracks&format=json")
   // @GET("?method=chart.gettoptracks&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json")

     fun getTracks(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("limit") limit: Int,
        @Query("page") page: Int
    ): Observable<TracksWrapper>


    @GET("?method=track.search&track&format=json")
    //?method=track.search&track=Believe&api_key=fe914f2966ff09f742c0a56811dcabc8&format=json
    fun getTrackFromSearch(
        @Path("artist") artist: String,
        @Query("api_key") apiKey: String,
        @Query("query") query: Int,
        @Query("page") page: Int
    ): Observable<TracksWrapper>
}




