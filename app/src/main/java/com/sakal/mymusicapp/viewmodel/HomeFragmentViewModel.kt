package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sakal.mymusicapp.App
import com.sakal.mymusicapp.domain.Audio
import com.sakal.mymusicapp.domain.Interactor


class HomeFragmentViewModel : ViewModel() {
    val audioListLiveData:  MutableLiveData<List<Audio>> = MutableLiveData()
    private var interactor: Interactor = App.instance.interactor

    init {
        val audio = interactor.getAudioDB()
        audioListLiveData.postValue(audio)
    }
}

