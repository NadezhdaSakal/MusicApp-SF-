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
import java.util.Collections.list

    class Interactor(private val repo: MainRepository, private val api: LastFMApi, private val preferences: PreferenceProvider) {
        var progressBarState: BehaviorSubject<Boolean> = BehaviorSubject.create()

        fun getTracksFromApi(page: Int) {
            progressBarState.onNext(true)
            //Метод getDefaultCategoryFromPreferences() будет нам получать при каждом запросе нужный нам список фильмов
            api.getTracks(getDefaultCategoryFromPreferences(),  50, page)
                .subscribeOn(Schedulers.io())
                .map {
                    Converter.convertApiTrackListToDTOList(it.tracks)
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

        fun getSearchResultFromApi(search: String): io.reactivex.rxjava3.core.Observable<List<Audio>> = api.getTrackFromSearch("", search, )
            .map {
                Converter.convertApiTrackListToDTOList(it.Track)
            }

        //Метод для сохранения настроек
        fun saveDefaultCategoryToPreferences(category: String) {
            preferences.saveDefaultCategory(category)
        }

        //Метод для получения настроек
        fun getDefaultCategoryFromPreferences() = preferences.getDefaultCategory()

        fun getTracksFromDB(): io.reactivex.rxjava3.core.Observable<List<Audio>> = repo.getAllFromDB()
    }
