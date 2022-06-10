package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.entity.TracksWrapper
import kotlinx.coroutines.flow.Flow

class TracksViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository
) : HomeFragmentViewModel() {
    private lateinit var _tracksFlow: Flow<PagingData<TracksWrapper>>
    val tracksFlow: Flow<PagingData<TracksWrapper>>
        get() = _tracksFlow

    init {
        getTracks()
    }

    private fun getTracks() = launchPagingAsync({
        mainRepository.gettracks().cachedIn(viewModelScope)
    }, {
        _tracksFlow = it
    })
}
