package com.example.thehiveapp_android.ui.yard

import android.content.Context
import android.widget.Button
import androidx.appcompat.widget.AppCompatButton

class YardButton(context: Context?) : AppCompatButton(context) {
    //rawX and rawY values of the last ACTION_DOWN event received.
    var downX : Float = -1.0f
    var downY : Float = -1.0f

}