package com.sakal.mymusicapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.view.notification.NotificationConstants
import com.sakal.mymusicapp.view.notification.NotificationHelper

class ReminderBroadcast : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {

        val bundle = intent?.getBundleExtra(NotificationConstants.TRACK_BUNDLE_KEY)
        val track: Audio = bundle?.get(NotificationConstants.TRACK_KEY) as Audio

        NotificationHelper.createNotification(context!!, track)
    }
}
