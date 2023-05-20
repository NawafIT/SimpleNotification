

package com.api.testnoti.ui

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.api.testnoti.AlarmSchedule
import com.api.testnoti.Constants
import com.api.testnoti.MainActivity
import com.api.testnoti.R
import com.api.testnoti.sendNotification
import java.util.*

//class AlarmReceiver : BroadcastReceiver() {
//
//    @SuppressLint("UnsafeProtectedBroadcastReceiver")
//    override fun onReceive(context: Context, intent: Intent) {
//        // Handle the notification logic here
//        val channelId = "notification_channel"
//
//        // Create a notification channel (required for Android Oreo and above)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            val channel = NotificationChannel(
//                channelId,
//                "Notification Channel",
//                NotificationManager.IMPORTANCE_HIGH
//            )
//            val notificationManager =
//                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
//            notificationManager.createNotificationChannel(channel)
//        }
//
//        // Create the notification
//        val notificationBuilder = NotificationCompat.Builder(context, channelId)
//            .setContentTitle(intent.getStringExtra("Noti"))
//            .setContentText(intent.getStringExtra("Noti2"))
//            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
//            .setPriority(NotificationCompat.PRIORITY_HIGH)
//            .setAutoCancel(true)
//
//        // Show the notification
//        NotificationManagerCompat.from(context).notify(0, notificationBuilder.build())
//
//    }
//}

class AlarmReceiver : BroadcastReceiver() {

    private lateinit var notificationManager: NotificationManager

    private lateinit var alarmSchedule: AlarmSchedule
    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ServiceCast", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        // Handle the notification logic here

        val message = intent.getStringExtra(Constants.MESSAGE_ARG)?:"test message"
        notificationManager = context.getSystemService(NotificationManager::class.java)
        alarmSchedule = AlarmSchedule(context)

        notificationManager.sendNotification(context,message!!)
        // Schedule the next day's notification
        alarmSchedule.reSchedule()
    }
}


