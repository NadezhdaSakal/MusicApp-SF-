package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sakal.mymusicapp.App
import com.sakal.mymusicapp.domain.Audio
import com.sakal.mymusicapp.domain.Interactor
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    val audioListLiveData: MutableLiveData<List<Audio>> = MutableLiveData()

    @Inject
    lateinit var interactor: Interactor

    init {
        App.instance.dagger.inject(this)
        interactor.getTracksFromApi(1, object : ApiCallback {
            override fun onSuccess(audio: List<Audio>) {
                audioListLiveData.postValue(audio)
            }

            override fun onFailure() {
            }
        })
    }

    interface ApiCallback {
        fun onSuccess(audio: List<Audio>)
        fun onFailure()
    }
}


