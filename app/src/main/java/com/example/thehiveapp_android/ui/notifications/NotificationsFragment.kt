package com.example.thehiveapp_android.ui.notifications

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.TimePicker
import androidx.appcompat.app.AppCompatActivity
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
 * @author Hannah
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
    @SuppressLint("SetTextI18n")
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

        // get our time and day of the week from the submitted form
        val timePicker: TimePicker = root.findViewById(R.id.notificationTimePicker)
        val dayPicker : MaterialDayPicker = root.findViewById(R.id.day_picker)
        dayPicker.selectionMode = SingleSelectionMode.create() // only allow one day of week to be chosen

        val setNotificationButton: Button = root.findViewById(R.id.notificationTimePickerButton)
        setNotificationButton.setOnClickListener{
            // get day of week from picker
            val dayInt = NotificationUtils.getDayInt(dayPicker.selectedDays.getOrElse(0) {Weekday.MONDAY})
            // create notification
            makeNotificationFromPicker(timePicker, dayInt)
            // add to notification list
            updateLst(root, timePicker, dayInt, "All notifications clear")

        }

        val cancelButton: Button = root.findViewById(R.id.notificationCancelButton)
        cancelButton.setOnClickListener{
            cancelNotifications()
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

        // get relevant time info from time picker and set to calendar
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, dayInt)
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            set(Calendar.SECOND, 0)
        }

        // notify user that notification has been set
        var str2 = timeTextView.text.toString()
        str2 += "Created notification for ${calendar.time}\n"
        timeTextView.text = str2

        // keep list of set notifications
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

    private var mNotified = false


     * Generates a new system notification based on the provided TimePicker.
     *
     * @param timePicker Time for the new notification to occur at
     * @param dayInt An int corresponding to a day of the week
     */
    private fun makeNotificationFromPicker(timePicker: TimePicker, dayInt : Int){
        val calendar: Calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, dayInt)
            set(Calendar.HOUR_OF_DAY, timePicker.hour)
            set(Calendar.MINUTE, timePicker.minute)
            this.set(Calendar.SECOND, 0)
        }
        var mNotificationTime = calendar.timeInMillis

        var act = this@NotificationsFragment.activity as AppCompatActivity
        if (!mNotified && act != null) {
            NotificationUtils.setNotification(mNotificationTime, act)
        }
    }

    /**
     * Clear out all notifications
     */
    fun cancelNotifications(){
        val calendar: Calendar = Calendar.getInstance();
        var mNotificationTime = calendar.timeInMillis

        var act = this@NotificationsFragment.activity as AppCompatActivity
        if (!mNotified && act != null) {
            NotificationUtils.deleteNotification(mNotificationTime, act)
        }
    }
}