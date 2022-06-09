package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagingData
import com.sakal.mymusicapp.App
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.TracksWrapper
import com.sakal.mymusicapp.domain.Interactor
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Executors
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val tracksListLiveData: MutableLiveData<List<Audio>> = MutableLiveData()

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        getTracks()
    }

    fun getTracks() {
        interactor.getTracksFromApi(1, object : ApiCallback {
            override fun onSuccess(tracks: List<Audio>) {
                tracksListLiveData.postValue(tracks)
            }

            override fun onFailure() {
                Executors.newSingleThreadExecutor().execute {
                    tracksListLiveData.postValue(interactor.getTracksFromDB())
                }
            }
        })
    }
    private lateinit var _tracksFlow: Flow<PagingData<TracksWrapper>>
    val tracksFlow: Flow<PagingData<TracksWrapper>>
        get() = _tracksFlow

    init {
        getTracks()
    }

    private fun getAllCharacters() = launchPagingAsync({
        MainRepository.getTracks().cachedIn(viewModelScope)
    }, {
        _tracksFlow = it
    })
}


interface ApiCallback {
        fun onSuccess(audio: List<Audio>)
        fun onFailure()

    }
}


