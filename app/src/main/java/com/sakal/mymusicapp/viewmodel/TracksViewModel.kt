package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.*
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sakal.mymusicapp.domain.Interactor
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository

class TracksViewModel(
    private val api: LastFMApi,
    private val repo: MainRepository
) : ViewModel() {

    val tracks =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            Interactor(repo, api)
        }).flow.cachedIn(viewModelScope)
}
