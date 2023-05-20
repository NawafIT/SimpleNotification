package com.api.testnoti

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat

fun NotificationManager.sendNotification(context: Context, message:String){

    val notificationBuilder = NotificationCompat.Builder(context, "test channel")
        .setContentTitle("test")
        .setContentText(message)
        .setSmallIcon(R.drawable.ic_baseline_notifications_24)
        .setPriority(NotificationCompat.PRIORITY_HIGH)
        .setAutoCancel(true)

// Show the notification
    notify(1,notificationBuilder.build())
}