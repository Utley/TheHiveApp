package com.example.thehiveapp_android.ui.notifications
import android.app.Dialog
import android.widget.TimePicker
import android.text.format.DateFormat
import android.app.TimePickerDialog
import android.app.DialogFragment
import java.util.*

import android.os.Bundle
import android.view.View

/**
 * Picks some time woooooooooo
 *
 * @author Hannah probably
 */
class TimePickerFragment : DialogFragment(), TimePickerDialog.OnTimeSetListener {

    /**
     * Generates th dialog to be used on object creation.
     *
     * @param savedInstanceState If this fragment is being re-constructed from a previous saved
     *  state bundle contains the necessary information to reconstruct it.
     */
    override fun onCreateDialog(savedInstanceState: Bundle): Dialog {
        // Use the current time as the default values for the picker
        val c = Calendar.getInstance()
        val hour = c.get(Calendar.HOUR_OF_DAY)
        val minute = c.get(Calendar.MINUTE)

        // Create a new instance of TimePickerDialog and return it
        return TimePickerDialog(activity, this, hour, minute, DateFormat.is24HourFormat(activity))
    }

    fun showTimePickerDialog(v: View) {
        TimePickerFragment().show(fragmentManager, "notificationTimePicker")
    }

    /**
     * Doesn't actually do anything, remove? Or is this branch just behind?
     */
    override fun onTimeSet(view: TimePicker, hourOfDay: Int, minute: Int) {
        // Do something with the time chosen by the user
    }
}
