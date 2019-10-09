package com.example.thehiveapp_android.ui.hiveForm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class HiveFormViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is hive form Fragment"
    }
    val text: LiveData<String> = _text
}
