package com.api.testnoti

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.SystemClock
import androidx.annotation.RequiresApi
import com.api.testnoti.ui.AlarmReceiver
import kotlin.time.Duration.Companion.seconds


class AlarmSchedule(private val context: Context) {

    @RequiresApi(Build.VERSION_CODES.M)
    private val alarmManager = context.getSystemService(AlarmManager::class.java)

    @RequiresApi(Build.VERSION_CODES.M)
    fun schedule(alarmItem: AlarmItem) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(Constants.MESSAGE_ARG, alarmItem.message)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            alarmItem.hashCode(),
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // TODO: Here you can specific time what you need
        val triggerTime = SystemClock.elapsedRealtime() + alarmItem.time
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,pendingIntent
        )
    }
    @RequiresApi(Build.VERSION_CODES.M)
    fun reSchedule() {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
//            putExtra(Constants.MESSAGE_ARG, alarmItem.message)
        }

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            3,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        // TODO: Here you can specific time what you need
        val triggerTime = SystemClock.elapsedRealtime() + 15.seconds.inWholeMilliseconds
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,pendingIntent
        )
    }
}
