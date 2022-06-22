package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sakal.mymusicapp.TracksDataSource
import com.sakal.mymusicapp.data.LastFMApi

class TracksViewModel(
    private val api: LastFMApi
) : ViewModel() {
    val tracks =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            TracksDataSource(api)
        }).flow.cachedIn(viewModelScope)
}
