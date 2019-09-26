package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

//This is meant to be an object that stores a single note about a hive.
open class HiveNoteRealmObject : RealmObject() {

    @PrimaryKey
    var uuid: Long = 0
    var noteString: String = "" //A pretty crappy default, but...
    var time: Date = Date()

}