package com.example.thehiveapp_android.ui.notifications

import androidx.appcompat.app.AppCompatActivity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import ca.antonious.materialdaypicker.MaterialDayPicker
import java.util.*

/**
 * Created based on example by devdeeds.com on 5/12/17.
 **/
class NotificationUtils {
    companion object {
        /**
         * Retrieves an int corresponding to the provided day of the week.
         *
         * Retrieves an int corresponding to the provided day of the week. The MaterialDayPicker library
         * lists selected days of the week via a `List<MaterialDayPicker.Weekday.DAY_OF_WEEK`, so we use
         * this function to quickly convert to an int to use in our notification setting.
         *
         * @param day The weekday we're looking to convert
         * @return An int corresponding to the above weekday
         */
        fun getDayInt(day: MaterialDayPicker.Weekday): Int {
            return when (day) {
                MaterialDayPicker.Weekday.SUNDAY -> 1
                MaterialDayPicker.Weekday.MONDAY -> 2
                MaterialDayPicker.Weekday.TUESDAY -> 3
                MaterialDayPicker.Weekday.WEDNESDAY -> 4
                MaterialDayPicker.Weekday.THURSDAY -> 5
                MaterialDayPicker.Weekday.FRIDAY -> 6
                MaterialDayPicker.Weekday.SATURDAY -> 7
            }
        }

        fun setNotification(timeInMilliSeconds: Long, activity: AppCompatActivity) {
            if (timeInMilliSeconds > 0) {
                val alarmManager = activity.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
                val alarmIntent = Intent(
                    activity.applicationContext,
                    AlarmReceiver::class.java
                )

                alarmIntent.putExtra("reason", "notification")
                alarmIntent.putExtra("timestamp", timeInMilliSeconds)

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = timeInMilliSeconds

                val pendingIntent = PendingIntent.getBroadcast(
                    activity,
                    0,
                    alarmIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
                )

                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            }
        }

        fun deleteNotification(timeInMilliSeconds: Long, activity: AppCompatActivity) {
            if (timeInMilliSeconds > 0) {
                val alarmManager = activity.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
                val alarmIntent = Intent(
                    activity.applicationContext,
                    AlarmReceiver::class.java
                )

                alarmIntent.putExtra("reason", "notification")
                alarmIntent.putExtra("timestamp", timeInMilliSeconds)

                val calendar = Calendar.getInstance()
                calendar.timeInMillis = timeInMilliSeconds

                val pendingIntent = PendingIntent.getBroadcast(
                    activity,
                    0,
                    alarmIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
                )

                alarmManager.cancel(pendingIntent)
            }
        }
    }
}