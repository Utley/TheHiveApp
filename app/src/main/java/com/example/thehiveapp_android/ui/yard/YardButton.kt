package com.example.thehiveapp_android.ui.yard

import android.content.Context
import android.widget.Button
import com.example.thehiveapp_android.data.HiveRealmObject

/**
 * Button-type object which allows accessing a hive from the yard view.
 *
 * @property context the current app context
 * @property hive The HiveRealmObject associated with this button
 * @property downX Button's x coordinate
 * @property downY Button's y coordinate
 */
class YardButton(context: Context?, val hive: HiveRealmObject) : Button(context) {
    //rawX and rawY values of the last ACTION_DOWN event received.
    var downX : Float = -1.0f
    var downY : Float = -1.0f
}