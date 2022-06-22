package com.sakal.mymusicapp.di

import com.sakal.mymusicapp.di.modules.DatabaseModule
import com.sakal.mymusicapp.di.modules.DomainModule
import com.sakal.mymusicapp.di.modules.RemoteModule
import com.sakal.mymusicapp.viewmodel.HomeFragmentViewModel
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        RemoteModule::class,
        DatabaseModule::class,
        DomainModule::class
    ]
)
interface AppComponent {
    //метод для того, чтобы появилась возможность внедрять зависимости в HomeFragmentViewModel
    fun inject(homeFragmentViewModel: HomeFragmentViewModel)
}





