package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.dao.TrackDao
import com.sakal.mymusicapp.data.entity.Audio
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import kotlinx.coroutines.flow.Flow

class MainRepository(private val trackDao: TrackDao) {

    fun putToDb(tracks: List<Audio>) {
        trackDao.insertAll(tracks)
    }

    fun getAllFromDB(): Observable<List<Audio>> = trackDao.getCachedTracks()

}

