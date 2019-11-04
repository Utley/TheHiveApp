package com.example.thehiveapp_android.ui.notifications

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.widget.TimePicker
import android.content.Intent
import com.example.thehiveapp_android.R
import java.util.*

/**
 * Created based on example by devdeeds.com on 5/12/17.
 */

class NotificationUtils {


    fun setNotification(timeInMilliSeconds: Long, activity: Activity) {

        //------------  alarm settings start  -----------------//

        if (timeInMilliSeconds > 0) {


            val alarmManager = activity.getSystemService(Activity.ALARM_SERVICE) as AlarmManager
            val alarmIntent = Intent(activity.applicationContext, AlarmReceiver::class.java) // AlarmReceiver1 = broadcast receiver

            alarmIntent.putExtra("reason", "notification")
            alarmIntent.putExtra("timestamp", timeInMilliSeconds)


            val calendar = Calendar.getInstance()
            calendar.timeInMillis = timeInMilliSeconds


            val custom_INTERVAL_ONE_MINUTE = AlarmManager.INTERVAL_FIFTEEN_MINUTES / 15
            val pendingIntent = PendingIntent.getBroadcast(activity, 0, alarmIntent, PendingIntent.FLAG_CANCEL_CURRENT)
            alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.timeInMillis, pendingIntent)
            /*
            alarmManager.setRepeating(
                AlarmManager.RTC_WAKEUP,
                calendar.timeInMillis,
                //AlarmManager.INTERVAL_HALF_HOUR,
                AlarmManager.INTERVAL_DAY,
                pendingIntent
                )
            */


        }

        //------------ end of alarm settings  -----------------//


    }
}