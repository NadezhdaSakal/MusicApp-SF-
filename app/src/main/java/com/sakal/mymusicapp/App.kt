package com.sakal.mymusicapp

import android.app.Application
import com.sakal.mymusicapp.di.AppComponent
import com.sakal.mymusicapp.di.DaggerAppComponent

class App : Application() {
    lateinit var dagger: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this
        dagger = DaggerAppComponent.create()
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
