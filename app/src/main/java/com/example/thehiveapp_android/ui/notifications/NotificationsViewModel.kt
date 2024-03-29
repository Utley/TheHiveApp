package com.example.thehiveapp_android.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Records the internal model for the Home Fragment.
 *
 * @property text Our current live data
 */
class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to HiveMind Reminders! Select the day and set the clock to the desired time below then click set to create your reminder."
    }

    /**
     * Current live data.
     */
    val text: LiveData<String> = _text

}