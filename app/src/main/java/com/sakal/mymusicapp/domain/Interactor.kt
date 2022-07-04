package com.sakal.mymusicapp.domain

import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.preference.PreferenceProvider
import com.sakal.mymusicapp.utils.Converter
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject

class Interactor(private val repo: MainRepository, private val api: LastFMApi, private val preferences: PreferenceProvider) {
    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getTracksFromApi(page: Int) {
        progressBarState.onNext(true)
        api.getTracks(getDefaultCategoryFromPreferences(),100, page )
            .subscribeOn(Schedulers.io())
            .map {
                Converter.convertApiTrackListToDTOList(it.tracks.track)
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

    fun getSearchResultFromApi(search: String): Observable<List<Audio>> = api.getTrackFromSearch("", search, 100, page = 1 )
        .map {
            Converter.convertApiTrackListToDTOList(it.tracks.track)
        }

    fun saveDefaultCategoryToPreferences(category: String) {
        preferences.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

    fun getTracksFromDB(): Observable<List<Audio>> = repo.getAllFromDB()
}
