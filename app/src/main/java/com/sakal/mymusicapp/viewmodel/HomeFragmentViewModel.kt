package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.sakal.mymusicapp.App
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel (
private val api: LastFMApi,
private val repo: MainRepository
) : ViewModel() {

    val tracks =
        Pager(config = PagingConfig(pageSize = 10, prefetchDistance = 2), pagingSourceFactory = {
            Interactor(repo, api)
        }).flow.cachedIn(viewModelScope)

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getTracks()
    }

    private fun getTracks() {
        TODO("Not yet implemented")
    }

    interface ApiCallback {
        fun onSuccess(audio: List<Audio>)
        fun onFailure()

    }
}