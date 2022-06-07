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

    fun doSearchPagination(
        visibleItemCount: Int,
        totalItemCount: Int,
        pastVisibleItemCount: Int,
        query: String
    ) {
        //Выясняем через переменную, нужна ли загрузка
        if (Interactor.needLoading) {
            //Если у нас при скролле список подходит к концу, то загружем еще порцию
            if ((visibleItemCount + pastVisibleItemCount) >= totalItemCount - 5) {
                Interactor.needLoading = false

                val page = currentlyLoadedSearchPage++
                if (page > totalPagersFromSearch) return

                showProgressBarLiveData.postValue(true)
                //Метод для получения фильмов с API, как видите, мы явно указываем, какую страницу
                //нужно загружать
                getDataFromSearch(query, page)
            }
        }
    }

    interface ApiCallback {
        fun onSuccess(audio: List<Audio>)
        fun onFailure()
    }
}


