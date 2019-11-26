package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

/**
 * Represents a reminder object's current status.
 *
 * Stores a Realm representation of a reminder's status. This object acts like a C struct, serving
 * only to group public fields in one place.
 *
 * @property uuid The reminder's database ID number
 * @property reminderTime The time the reminder's set for
 * @property reminderString The string the reminder will display on activation.
 * @author Zac
 */
open class ReminderRealmObject : RealmObject() {
    //These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = 0

    var reminderTime : Date = Date()
    var reminderString : String = "You've got a reminder set for right now!"

}