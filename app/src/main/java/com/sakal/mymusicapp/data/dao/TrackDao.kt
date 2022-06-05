package com.sakal.mymusicapp.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sakal.mymusicapp.data.entity.Audio

@Dao
interface TrackDao {
    @Query("SELECT * FROM cached_tracks")
    fun getCachedTracks(): List<Audio>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(list: List<Audio>)
}
