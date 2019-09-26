package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

//RealmObject() descendants must be open classes; Kotlin classes are final by default
open class HiveRealmObject() : RealmObject() {
    //These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = 0
    var name: String = "My Hive"
    var createdAt: Date = Date()
    var lastAttendedAt: Date = Date() //Date() takes on the "current" date (i.e when it's called).
    //Much of this is "when was the last time I did ___?", so we're going to need to save many dates

    //For the rest of this, I'm honestly spitballing a bit.
    var estimatedPopulation: Int? = null
    var queenBirthday: Date? = null
    var lastMiteInspection: Date? = null
    var honeyLastHarvestedAt: Date? = null

    //var location: IDontKnowWhatType? = null
    // What we should do probably depends on where we are because of temperature.



}