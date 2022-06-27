package com.sakal.mymusicapp.viewmodel

import androidx.lifecycle.ViewModel
import com.sakal.mymusicapp.App
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.domain.Interactor
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.BehaviorSubject
import javax.inject.Inject

class HomeFragmentViewModel : ViewModel() {
    @Inject
    lateinit var interactor: Interactor
    val tracksListData: Observable<List<Audio>>
    val showProgressBar: BehaviorSubject<Boolean>

    init {
        App.instance.dagger.inject(this)
        showProgressBar = interactor.progressBarState
        tracksListData = interactor.getTracksFromDB()
        getTracks()
    }

    fun getTracks() {
        interactor.getTracksFromApi(1)
    }

    fun getSearchResult(search: String) = interactor.getSearchResultFromApi(search)

}
