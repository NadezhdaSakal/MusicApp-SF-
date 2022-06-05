package com.sakal.mymusicapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sakal.mymusicapp.data.dao.TrackDao
import com.sakal.mymusicapp.data.entity.Audio

@Database(entities = [Audio::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun trackDao(): TrackDao
}
