package com.sakal.mymusicapp.di

import dagger.Component
import com.sakal.mymusicapp.view.MainActivity
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelProviderModule::class
    ]
)
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}
