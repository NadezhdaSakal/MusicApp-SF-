package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.*
import com.sakal.mymusicapp.data.Entity.dto.ArtistsWrapper
import com.sakal.mymusicapp.data.Entity.dto.TracksWrapper
import com.sakal.mymusicapp.utils.Converter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class Interactor(private val repo: MainRepository, private val retrofitService: LastFMApi) {

    fun getArtistsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTopArtists(limit = 50, page).enqueue(object : Callback<ArtistsWrapper> {
            override fun onResponse(
                call: Call<ArtistsWrapper>,
                response: Response<ArtistsWrapper>
            ) {
                callback.onSuccess(Converter.convertApiListToDTOList(response.body()?.artists?.artist))
            }

            override fun onFailure(call: Call<ArtistsWrapper>, t: Throwable) {
                callback.onFailure()
            }
        })
    }

    fun getTracksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTracksArtist(mbid, page).enqueue(object : Callback<TracksWrapper> {
            override fun onResponse
                        (call: Call<TracksWrapper>, response: Response<TracksWrapper>) {
                callback.onSuccess(Converter.convertApiListToDTOList(response.body()?.tracks?.track))
            }

            override fun onFailure(call: Call<TracksWrapper>, t: Throwable) {
                callback.onFailure()
            }
        })
    }


}





