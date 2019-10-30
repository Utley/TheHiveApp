package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

open class ReminderRealmObject : RealmObject() {
    //These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = 0

    var reminderTime : Date = Date()
    var reminderString : String = "You've got a reminder set for right now!"

}