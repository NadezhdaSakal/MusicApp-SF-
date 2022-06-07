package com.sakal.mymusicapp

import android.app.Application
import com.sakal.mymusicapp.di.AppComponent
import com.sakal.mymusicapp.di.DaggerAppComponent
import com.sakal.mymusicapp.di.modules.RemoteModule
import com.sakal.mymusicapp.di.modules.DomainModule
import com.sakal.mymusicapp.di.modules.DatabaseModule




class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.builder()
            .remoteModule(RemoteModule())
            .databaseModule(DatabaseModule())
            .domainModule(DomainModule(this))
            .build()

    }

    companion object {
        lateinit var instance: App
            private set
    }
}
