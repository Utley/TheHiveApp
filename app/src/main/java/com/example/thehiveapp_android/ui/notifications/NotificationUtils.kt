package com.example.thehiveapp_android.ui.notifications

import androidx.appcompat.app.AppCompatActivity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import ca.antonious.materialdaypicker.MaterialDayPicker
import java.util.*

/**
 * Manages helper utilities of notification functionality including notification
 * setting, deletion, and retrieving integer equivalent of day of the week
 *
 * Created based on open source example by devdeeds.com
 *
 * @author Hannah
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


        /**
         * Given a time, create a system notification using Android AlarmManager
         *
         * @param timeInMilliSeconds Time, from Day and Time Pickers, to set notification
         * @param activity Current activity
         */
        fun setNotification(timeInMilliSeconds: Long, activity: Activity) {
            // if time picker was set
            if (timeInMilliSeconds > 0) {
                val alarmManager = activity.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
                val alarmIntent = Intent(
                    activity.applicationContext,
                    AlarmReceiver::class.java
                )
                // associate the notification message and time to the alarm
                alarmIntent.putExtra("reason", "notification")
                alarmIntent.putExtra("timestamp", timeInMilliSeconds)

                // create calendar object with value of timeInMillis
                // so we can use it for our alarm value
                val calendar = Calendar.getInstance()
                calendar.timeInMillis = timeInMilliSeconds

                val pendingIntent = PendingIntent.getBroadcast(
                    activity,
                    0,
                    alarmIntent,
                    PendingIntent.FLAG_CANCEL_CURRENT
                )
                // set our notification!
                alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            }
        }

        /**
         * Clear all reminders after the given time
         * @param timeInMilliSeconds Time before which all notifications should be removed
         * @param activity Reference to the current activity (given to Intent)
         */
        fun deleteNotification(timeInMilliSeconds: Long, activity: Activity) {
            // if time is given
            if (timeInMilliSeconds > 0) {
                val alarmManager = activity.getSystemService(AppCompatActivity.ALARM_SERVICE) as AlarmManager
                val alarmIntent = Intent(
                    activity.applicationContext,
                    AlarmReceiver::class.java
                )

                // associate message and time of alarms to delete
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