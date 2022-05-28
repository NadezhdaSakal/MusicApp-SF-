package com.sakal.mymusicapp.di.modules

import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.data.PhishAPI
import com.sakal.mymusicapp.domain.Interactor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DomainModule {
    @Singleton
    @Provides
    fun provideInteractor(repository: MainRepository, phishAPI: PhishAPI) = Interactor(repo = repository, retrofitService = phishAPI)
}
