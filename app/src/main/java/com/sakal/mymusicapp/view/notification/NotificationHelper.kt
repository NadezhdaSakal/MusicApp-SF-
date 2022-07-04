package com.sakal.mymusicapp.view.notification

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.sakal.mymusicapp.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.sakal.mymusicapp.data.entity.Audio
import com.sakal.mymusicapp.view.MainActivity
import android.app.AlarmManager
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.os.Bundle
import com.sakal.mymusicapp.receivers.ReminderBroadcast
import java.util.*

object NotificationHelper {
    fun createNotification(context: Context, audio: Audio) {
        val mIntent = Intent(context, MainActivity::class.java)

        val pendingIntent =
            PendingIntent.getActivity(context, 0, mIntent, PendingIntent.FLAG_UPDATE_CURRENT)

        val builder = NotificationCompat.Builder(context!!, NotificationConstants.CHANNEL_ID).apply {
            setSmallIcon(R.drawable.ic_baseline_latest)
            setContentTitle("Новое!")
            setContentText(audio.track)
            priority = NotificationCompat.PRIORITY_DEFAULT
            setContentIntent(pendingIntent)
            setAutoCancel(true)
        }

        val notificationManager = NotificationManagerCompat.from(context)

        Glide.with(context)

            .asBitmap()
            .load(audio.image)
            .into(object : CustomTarget<Bitmap>() {
                override fun onLoadCleared(placeholder: Drawable?) {
                }
                override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                    builder.setStyle(NotificationCompat.BigPictureStyle().bigPicture(resource))
                    notificationManager.notify(audio.id, builder.build())
                }
            })
        notificationManager.notify(audio.id, builder.build())
    }

    fun notificationSet(context: Context, audio: Audio) {
        val calendar = Calendar.getInstance()
        val currentYear = calendar.get(Calendar.YEAR)
        val currentMonth = calendar.get(Calendar.MONTH)
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentHour = calendar.get(Calendar.HOUR_OF_DAY)
        val currentMinute = calendar.get(Calendar.MINUTE)

        DatePickerDialog(
            context,
            { _, dpdYear, dpdMonth, dayOfMonth ->
                val timeSetListener =
                    TimePickerDialog.OnTimeSetListener { _, hourOfDay, pickerMinute ->
                        val pickedDateTime = Calendar.getInstance()
                        pickedDateTime.set(
                            dpdYear,
                            dpdMonth,
                            dayOfMonth,
                            hourOfDay,
                            pickerMinute,
                            0
                        )
                        val dateTimeInMillis = pickedDateTime.timeInMillis
                        createLatestEvent(context, dateTimeInMillis, audio)
                    }

                TimePickerDialog(
                    context,
                    timeSetListener,
                    currentHour,
                    currentMinute,
                    true
                ).show()

            },
            currentYear,
            currentMonth,
            currentDay
        ).show()
    }

    private fun createLatestEvent(context: Context, dateTimeInMillis: Long, audio: Audio) {
        val alarmManager =
            context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(audio.track, null, context, ReminderBroadcast()::class.java)
        val bundle = Bundle()
        bundle.putParcelable(NotificationConstants.TRACK_KEY, audio)
        intent.putExtra(NotificationConstants.TRACK_BUNDLE_KEY, bundle)
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        alarmManager.set(
            AlarmManager.RTC_WAKEUP,
            dateTimeInMillis,
            pendingIntent
        )
    }
}
