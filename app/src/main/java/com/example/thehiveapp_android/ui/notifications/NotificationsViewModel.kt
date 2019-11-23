package com.example.thehiveapp_android.ui.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 *
 *
 * @author I forgot how to check
 */
class NotificationsViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "Welcome to HiveMind Reminders! Set the clock to the desired time below then click set to create your reminder."
    }
    val text: LiveData<String> = _text

}