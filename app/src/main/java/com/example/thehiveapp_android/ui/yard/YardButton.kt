package com.example.thehiveapp_android.ui.yard

import android.content.Context
import android.widget.Button

class YardButton(context: Context?) : Button(context) {
    //rawX and rawY values of the last ACTION_DOWN event received.
    var downX : Float = -1.0f
    var downY : Float = -1.0f

}