

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
import com.api.testnoti.MainActivity
import com.api.testnoti.R
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

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("ServiceCast", "UnsafeProtectedBroadcastReceiver")
    override fun onReceive(context: Context, intent: Intent) {
        // Handle the notification logic here
        val channelId = "notification_channel"

        // Create a notification channel (required for Android Oreo and above)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                channelId,
                "Notification Channel",
                NotificationManager.IMPORTANCE_HIGH
            )
            val notificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }

        // Create the notification
        val notificationBuilder = NotificationCompat.Builder(context, channelId)
            .setContentTitle(intent.getStringExtra("Noti"))
            .setContentText(intent.getStringExtra("Noti2"))
            .setSmallIcon(R.drawable.ic_baseline_notifications_24)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)

        // Show the notification
        NotificationManagerCompat.from(context).notify(0, notificationBuilder.build())

        // Schedule the next day's notification
        val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val id = intent.getIntExtra("id",0)
        val timeValue = intent.getStringExtra("time")
        // Get the original PendingIntent from the intent
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            id,
            intent,
            PendingIntent.FLAG_IMMUTABLE
        )

        val parts = timeValue?.split(":")
        val hours = parts?.get(0)?.toInt()
        val minutes = parts?.get(1)?.toInt()
        // Calculate the time for the next day
        val calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            add(Calendar.DAY_OF_YEAR, 1)
            set(Calendar.HOUR_OF_DAY, hours!!) // Set the desired hour for the next day's notification
            set(Calendar.MINUTE, minutes!!) // Set the desired minute for the next day's notification
            set(Calendar.SECOND, 0)
        }

        // Set the next day's alarm using the updated time
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        } else {
            alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                pendingIntent
            )
        }
    }
}





