package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository

class TracksViewModelFactory (
    private val api: LastFMApi,
    private val repo: MainRepository
    ): ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeFragmentViewModel() as T
        }
    }
