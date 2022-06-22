package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sakal.mymusicapp.data.LastFMApi

class TracksViewModelFactory (
    private val api: LastFMApi
    ): ViewModelProvider.NewInstanceFactory(){

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return TracksViewModel(api) as T
        }
    }
