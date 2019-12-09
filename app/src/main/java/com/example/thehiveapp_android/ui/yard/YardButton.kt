package com.example.thehiveapp_android.ui.yard

import android.content.Context
import android.widget.Button
import com.example.thehiveapp_android.data.HiveRealmObject

class YardButton(context: Context?, val hive: HiveRealmObject) : Button(context) {
    //rawX and rawY values of the last ACTION_DOWN event received.
    var downX : Float = -1.0f
    var downY : Float = -1.0f
}