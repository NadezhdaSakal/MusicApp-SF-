package com.sakal.mymusicapp.domain

import android.database.Observable
import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.data.entity.Track
import com.sakal.mymusicapp.data.preference.PreferenceProvider
import com.sakal.mymusicapp.utils.Converter
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import io.reactivex.rxjava3.subjects.BehaviorSubject
import java.util.Collections.list

class Interactor(private val repo: MainRepository, private val api: LastFMApi, private val preference: PreferenceProvider) {


    var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

    fun getTracksFromApi(page: Int) {
        progressBarState.onNext(true)
        api.getTracks(getDefaultCategoryFromPreferences(), limit = 100, page)
            .subscribeOn(Schedulers.io())
            .map {
                Converter.convertApiTrackListToDTOList(list<Track>)
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
        api.getTracksFromSearch(list<Track>)
            .map {
                Converter.convertApiTrackListToDTOList(it.)
            }

    fun saveDefaultCategoryToPreferences(category: String) {
        preference.saveDefaultCategory(category)
    }

    fun getDefaultCategoryFromPreferences() = preference.getDefaultCategory()

    fun getTracksFromDB(): io.reactivex.rxjava3.core.Observable<List<Audio>> = repo.getAllFromDB()
}
