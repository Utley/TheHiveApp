package com.example.thehiveapp_android.ui.notifications

import android.os.Bundle
import android.os.Message
import android.provider.DocumentsContract
import android.service.media.MediaBrowserService
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.thehiveapp_android.R

import java.util.*

class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        notificationsViewModel =
            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
        val textView: TextView = root.findViewById(R.id.text_notifications)
        notificationsViewModel.text.observe(this, Observer {
            textView.text = it
        })

/*
        val button: Button = root.findViewById(R.id.button_notification)

        button.setOnClickListener {
            makeNotification()
        }

 */

        val timePicker: TimePicker = root.findViewById(R.id.notificationTimePicker)

        val timeButton: Button = root.findViewById(R.id.notificationTimePickerButton)
        timeButton.setOnClickListener{
            clickTest(it, timePicker)
            updateLst(root, timePicker)

        }


        return root
    }

    fun updateLst(root: View, timePicker: TimePicker){
        val timeTextView: TextView = root.findViewById(R.id.time_notification) as TextView

        val calendar: Calendar = Calendar.getInstance().apply {
            //timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            //set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE)+1)
            set(Calendar.SECOND, 0)
        }

        var str2 = timeTextView.text.toString()
        str2 += "Created notification for ${calendar.time}\n"
        timeTextView.text = str2
//
        var strLst = str2.split("\n")
        val iterator = strLst.iterator()


        var string = ""

        // do something with the rest of elements
        iterator.forEach {
            //if(not removed)
            string += it
            string += "\n"
            //println("The element is $it")
        }
        timeTextView.text = string
//

    }

    //private val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
    private var mNotified = false

    fun clickTest(v: View, timePicker: TimePicker){
        println("Hello World 1")
        makeNotificationFromPicker(timePicker)
    }


    fun makeNotification(){
        println("Hello World 2")

        val calendar: Calendar = Calendar.getInstance().apply {
            //timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 3)
            //set(Calendar.MINUTE, 30)
            set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE)+1)
            set(Calendar.SECOND, 0)
        }

        // var mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
        var mNotificationTime = calendar.timeInMillis //Set to 2:20 AM


        var act = this@NotificationsFragment.activity
        if (!mNotified && act != null) {
            NotificationUtils().setNotification(mNotificationTime, act)
        }

    }

    fun makeNotificationFromPicker(timePicker: TimePicker){
        println("Hello World 3")

        val calendar: Calendar = Calendar.getInstance().apply {
            //timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            //set(Calendar.MINUTE, Calendar.getInstance().get(Calendar.MINUTE)+1)
            set(Calendar.SECOND, 0)
        }

        // var mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
        var mNotificationTime = calendar.timeInMillis //Set to 2:20 AM

        println("Creating notification for ${calendar.time}")
        println("Real time is ${Calendar.getInstance().time}")


        var act = this@NotificationsFragment.activity
        if (!mNotified && act != null) {
            NotificationUtils().setNotification(mNotificationTime, act)
        }

    }
}