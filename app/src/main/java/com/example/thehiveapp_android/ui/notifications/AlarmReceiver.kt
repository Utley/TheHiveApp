package com.example.thehiveapp_android.ui.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/**
 * Specifies behavior on reception of an alarm broadcast.
 *
 * @author Hannah
 **/
class AlarmReceiver : BroadcastReceiver() {

    /**
     * Reacts to the reception of an alarm.
     *
     * @param context The current context
     * @param intent The alarm's intent
     */
    override fun onReceive(context: Context, intent: Intent) {

        println("Received Alarm")

        val service = Intent(context, NotificationService::class.java)
        service.putExtra("reason", intent.getStringExtra("reason"))
        service.putExtra("timestamp", intent.getLongExtra("timestamp", 0))

        context.startService(service)
    }
}