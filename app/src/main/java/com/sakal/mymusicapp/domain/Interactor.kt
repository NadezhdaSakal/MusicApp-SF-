package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.*
import com.sakal.mymusicapp.data.Entity.dto.Results
import com.sakal.mymusicapp.utils.Converter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Interactor(private val repo: MainRepository, private val retrofitService: LastFMApi) {

    fun getTracksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTracksArtist(api_Key = , artist = ).enqueue(object : Callback<Results> {
            override fun onResponse(call: Call<Results>, response: Response<Results>) {
                callback.onSuccess(Converter.convertApiListToDTOList(response.body()?.LastFMTracks))
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}

