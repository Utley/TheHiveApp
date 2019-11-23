package com.example.thehiveapp_android.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * Records the internal model for the Home Fragment.
 *
 * @author I forgot how to check
 */
class HomeViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is home Fragment"
    }

    /**
     * Current live data.
     */
    val text: LiveData<String> = _text
}