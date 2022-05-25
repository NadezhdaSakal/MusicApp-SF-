package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.*
import com.sakal.mymusicapp.data.Entity.JamendoResultsDto
import com.sakal.mymusicapp.utils.Converter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Interactor (private val repo: MainRepository, private val retrofitService: JamendoAPI) {

    fun getTracksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTracks("apiKey",   "ru-RU", page).enqueue(object : Callback<JamendoResultsDto> {
            override fun onResponse(call: Call<JamendoResultsDto>, response: Response<JamendoResultsDto>) {
                callback.onSuccess(Converter.convertApiListToDTOList(response.body()?.JamendoTracks))
            }

            override fun onFailure(call: Call<JamendoResultsDto>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}
