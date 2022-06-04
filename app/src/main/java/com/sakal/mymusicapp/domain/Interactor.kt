package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.*
import com.sakal.mymusicapp.data.Entity.TracksWrapper
import com.sakal.mymusicapp.utils.Converter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor(private val repo: MainRepository, private val retrofitService: LastFMApi) {

    fun getTracksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTracks(limit = 50, page).enqueue(object : Callback<TracksWrapper> {
            override fun onResponse(call: Call<TracksWrapper>, response: Response<TracksWrapper>) {
                callback.onSuccess(Converter.convertApiTrackListToDTOList(response.body()?.tracks?.track))
            }

            override fun onFailure(call: Call<TracksWrapper>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}





