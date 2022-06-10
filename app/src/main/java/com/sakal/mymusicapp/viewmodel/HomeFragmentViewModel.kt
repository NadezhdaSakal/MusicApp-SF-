package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.sakal.mymusicapp.App
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.TracksWrapper
import com.sakal.mymusicapp.domain.Interactor
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModelViewModel @ViewModelInject constructor(
    private val mainRepository: MainRepository:
) : ViewModel() {

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getTracks()
    }
    private lateinit var _tracksFlow: Flow<PagingData<TracksWrapper>>
    val tracksFlow: Flow<PagingData<TracksWrapper>>
        get() = _tracksFlow

    init {
        getTracks()
    }

    private fun getTracks() = launchPagingAsync({
        MainRepository.getTracks().cachedIn(viewModelScope)
    }, {
        _tracksFlow = it
    })

    interface ApiCallback {
        fun onSuccess(audio: List<Audio>)
        fun onFailure()

    }
}


