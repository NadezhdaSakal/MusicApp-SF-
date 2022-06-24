package com.sakal.mymusicapp.di.modules

import com.sakal.mymusicapp.data.LastFMApi
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton
import android.content.Context

@Module
class DomainModule(val context: Context) {
    @Provides
    fun provideContext() = context

    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, LastFMApi: LastFMApi) =
        Interactor(repo = repository, api = LastFMApi)
}
