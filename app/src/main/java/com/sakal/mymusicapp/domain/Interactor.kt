package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.*
import com.sakal.mymusicapp.data.Entity.PhishResultsDto
import com.sakal.mymusicapp.utils.Converter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log

class Interactor (private val repo: MainRepository, private val retrofitService: PhishAPI) {

    fun getTracksFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTracks().enqueue(object : Callback<PhishResultsDto> {
            override fun onResponse(call: Call<PhishResultsDto>, response: Response<PhishResultsDto>) {
                callback.onSuccess(Converter.convertApiListToDTOList(response.body()?.phishTracks))
            }

            override fun onFailure(call: Call<PhishResultsDto>, t: Throwable) {
                Log.e("retrofit", t.toString())
                callback.onFailure()
            }
        })
    }
}
