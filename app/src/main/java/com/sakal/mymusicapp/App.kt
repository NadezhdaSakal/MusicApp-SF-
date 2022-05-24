package com.sakal.mymusicapp

import android.app.Application
import com.sakal.mymusicapp.data.MainRepository
import com.sakal.mymusicapp.domain.Interactor


class App : Application() {
    lateinit var repo: MainRepository
    lateinit var interactor: Interactor

    override fun onCreate() {
        super.onCreate()
        instance = this
        repo = MainRepository()
        interactor = Interactor(repo)
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
