package com.sakal.mymusicapp.di.modules

import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.Context
import com.sakal.mymusicapp.data.preference.PreferenceProvider

@Module
class DomainModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideInteractor(
        repository: MainRepository,
        LastFMApi: LastFMApi,
        preferenceProvider: PreferenceProvider
    ) = Interactor(repo = repository, api = LastFMApi, preferences = preferenceProvider)
}
