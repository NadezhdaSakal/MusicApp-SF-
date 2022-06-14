package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sakal.mymusicapp.TracksPagingDataSource
import com.sakal.mymusicapp.data.LastFMApi

class TracksViewModel (
private val api: LastFMApi
) : HomeFragmentViewModel() {
    val tracks =
        Pager(config = PagingConfig(pageSize = 10), pagingSourceFactory = {
            TracksPagingDataSource (api)
        }).flow.cachedIn(viewModelScope)
}

