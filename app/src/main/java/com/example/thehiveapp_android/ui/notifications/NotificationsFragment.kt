package com.example.thehiveapp_android.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import ca.antonious.materialdaypicker.MaterialDayPicker
import ca.antonious.materialdaypicker.MaterialDayPicker.Weekday
import ca.antonious.materialdaypicker.SingleSelectionMode
import com.example.thehiveapp_android.R
import java.util.*

/**
 * Manages the visual representation of the notification page.
 *
 * @author I forgot how to check
 */
class NotificationsFragment : Fragment() {

    private lateinit var notificationsViewModel: NotificationsViewModel

    /**
     * Called to have the fragment instantiate its user interface view. This will be called between
     * `onCreate(Bundle)` and `onActivityCreated(Bundle)`.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the
     * fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be
     * attached to.  The fragment should not add the view itself, but this can be used to generate
     * the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
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

        val timePicker: TimePicker = root.findViewById(R.id.notificationTimePicker)
        val dayPicker : MaterialDayPicker = root.findViewById(R.id.day_picker)
        dayPicker.selectionMode = SingleSelectionMode.create() // only allow one day of week to be chosen

        val timeButton: Button = root.findViewById(R.id.notificationTimePickerButton)
        timeButton.setOnClickListener{
            // get day of week from picker
            val dayInt = NotificationUtils.getDayInt(dayPicker.selectedDays.getOrElse(0) {Weekday.MONDAY})
            // create notification
            clickTest(it, timePicker, dayInt)
            // add to notification list
            updateLst(root, timePicker, dayInt, "All notifications clear")

        }

        val cancelButton: Button = root.findViewById(R.id.notificationCancelButton)
        cancelButton.setOnClickListener{
            cancelNotificationFromPicker(timePicker)
            val timeTextView: TextView = root.findViewById(R.id.time_notification) as TextView
            timeTextView.text = "All notifications clear\n"
        }

        return root
    }


    /**
     * Eliminates the string rmvStr from the list of current notifications to display.
     *
     * @param root The current View context
     * @param timePicker Contains the time to update
     * @param dayInt An int corresponding to a day of the week
     * @param rmvStr String to remove
     */
    private fun updateLst(root: View, timePicker: TimePicker, dayInt: Int, rmvStr: String){
        val timeTextView: TextView = root.findViewById(R.id.time_notification) as TextView

        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, dayInt)
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            set(Calendar.SECOND, 0)
        }

        var str2 = timeTextView.text.toString()
        str2 += "Created notification for ${calendar.time}\n"
        timeTextView.text = str2

        val strLst = str2.split("\n")
        val iterator = strLst.iterator()


        var string = ""

        iterator.forEach {
            if(rmvStr != it){
                string += it
                string += "\n"
            }

        }

        timeTextView.text = string
    }

    //private val mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
    private var mNotified = false


    /**
     * Test function, delete later
     */
    fun clickTest(v: View, timePicker: TimePicker, dayInt : Int ){
        println("Hello World 1")
        makeNotificationFromPicker(timePicker, dayInt)
    }

    /**
     * Generates a new system notification based on the provided TimePicker.
     *
     * @param timePicker Time for the new notification to occur at
     * @param dayInt An int corresponding to a day of the week
     */
    fun makeNotificationFromPicker(timePicker: TimePicker, dayInt : Int){
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, dayInt)
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            this.set(Calendar.SECOND, 0)
        }

        // var mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
        var mNotificationTime = calendar.timeInMillis

        println("Creating notification for ${calendar.time}")
        println("Real time is ${Calendar.getInstance().time}")


        var act = this@NotificationsFragment.activity
        if (!mNotified && act != null) {
            NotificationUtils.setNotification(mNotificationTime, act)
        }
    }

    /**
     * Not implemented yet?
     */
    fun cancelNotificationFromPicker(timePicker: TimePicker){
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            set(Calendar.SECOND, 0)
        }

        // var mNotificationTime = Calendar.getInstance().timeInMillis + 5000 //Set after 5 seconds from the current time.
        var mNotificationTime = calendar.timeInMillis

        println("Creating notification for ${calendar.time}")
        println("Real time is ${Calendar.getInstance().time}")


        var act = this@NotificationsFragment.activity
        if (!mNotified && act != null) {
            NotificationUtils.deleteNotification(mNotificationTime, act)
        }
    }
}