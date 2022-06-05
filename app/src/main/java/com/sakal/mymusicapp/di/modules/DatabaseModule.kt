package com.sakal.mymusicapp.di.modules

import com.sakal.mymusicapp.data.MainRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.Context
import androidx.room.Room
import com.sakal.mymusicapp.data.dao.TrackDao
import com.sakal.mymusicapp.data.db.AppDatabase

@Module
class DatabaseModule {
    @Singleton
    @Provides
    fun provideTrackDao(context: Context) =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "track_db"
        ).build().trackDao()

    @Provides
    @Singleton
    fun provideRepository(trackDao: TrackDao) = MainRepository(trackDao)
}
