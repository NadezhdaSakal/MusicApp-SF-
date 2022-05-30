package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.Entity.PhishResultsDto
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface PhishAPI {
    @Headers(
        "Authorization: Bearer 82c652413a20c216e4e3d89da8263792f9218872328d7aa31df953961e47807237b75ebc5a55121be7cd7f6678f4be1a",
        "Content-Type: application/json"
    )
    @GET("tracks")
    fun getTracks (): Call<PhishResultsDto>
}


