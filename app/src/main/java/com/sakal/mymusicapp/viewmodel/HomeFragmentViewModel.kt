package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sakal.mymusicapp.App
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.domain.Interactor
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

    interface ApiCallback {
        fun onSuccess(audio: List<Audio>)
        fun onFailure()

    }
}


