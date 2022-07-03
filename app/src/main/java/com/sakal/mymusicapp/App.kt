package com.sakal.mymusicapp

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.sakal.mymusicapp.di.AppComponent
import com.sakal.mymusicapp.di.DaggerAppComponent
import com.sakal.mymusicapp.di.modules.RemoteModule
import com.sakal.mymusicapp.di.modules.DomainModule
import com.sakal.mymusicapp.di.modules.DatabaseModule
import com.sakal.mymusicapp.view.notification.NotificationConstants.CHANNEL_ID

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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "LatestChannel"
            val descriptionText = "MyMusicApp notification Channel"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val mChannel = NotificationChannel(CHANNEL_ID, name, importance)
            mChannel.description = descriptionText
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }
    }

    companion object {
        lateinit var instance: App
            private set
    }
}
