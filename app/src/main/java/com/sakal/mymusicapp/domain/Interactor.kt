package com.sakal.mymusicapp.domain

import android.database.Observable
import com.sakal.mymusicapp.data.API
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.data.preference.PreferenceProvider
import com.sakal.mymusicapp.utils.Converter
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class Interactor(private val repo: MainRepository, private val api: LastFMApi, private val preference: PreferenceProvider) {


    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getTracksFromApi(page: Int) {
        //Показываем ProgressBar
        progressBarState.onNext(true)
        //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
        api.getTracks(getDefaultCategoryFromPreferences(), limit = 100, page)
            .subscribeOn(Schedulers.io())
            .map {
                Converter.convertApitrackListToDTOList()
            }
            .subscribeBy(
                onError = {
                    progressBarState.onNext(false)
                },
                onNext = {
                    progressBarState.onNext(false)
                    repo.putToDb(it)
                }
            )
    }


    fun getSearchResultFromApi(search: String): Observable<List<Track>> =
        api.getTracksFromSearch()
            .map {
                Converter.convertApiTrackListToDTOList(it.)
            }

    fun saveDefaultCategoryToPreferences(category: String) {
        preference.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preference.getDefaultCategory()

    fun getTracksFromDB(): List<Audio> = repo.getAllFromDB()
}
