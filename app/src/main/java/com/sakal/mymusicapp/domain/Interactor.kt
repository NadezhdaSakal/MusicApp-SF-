package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.*
import com.sakal.mymusicapp.data.Entity.dto.Artists
import com.sakal.mymusicapp.data.Entity.dto.Results
import com.sakal.mymusicapp.utils.Converter
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class Interactor(private val repo: MainRepository, private val retrofitService: LastFMApi) {

    fun getArtistsFromApi(page: Int, callback: HomeFragmentViewModel.ApiCallback) {
        retrofitService.getTopArtists().enqueue(object : Callback<Artists> {
            override fun onResponse(call: Call<Artists>, response: Response<Artists>) {
                callback.onSuccess(Converter.convertApiListToDTOList(response?.Artist))
            }

            override fun onFailure(call: Call<Results>, t: Throwable) {
                callback.onFailure()
            }
        })
    }
}

