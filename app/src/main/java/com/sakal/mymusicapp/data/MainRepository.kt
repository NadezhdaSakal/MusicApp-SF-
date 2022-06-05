package com.sakal.mymusicapp.data

import com.sakal.mymusicapp.data.dao.TrackDao
import com.sakal.mymusicapp.data.entity.Audio
import java.util.concurrent.Executors


class MainRepository(private val trackDao: TrackDao) {

    fun putToDb(tracks: List<Audio>) {
        Executors.newSingleThreadExecutor().execute {
            trackDao.insertAll(tracks)
        }
    }

    fun getAllFromDB(): List<Audio> {
        return trackDao.getCachedTracks()
    }
}
