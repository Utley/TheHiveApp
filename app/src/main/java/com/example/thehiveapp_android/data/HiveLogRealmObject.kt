package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

//This is meant to be an object that stores a single note about a hive.
open class HiveLogRealmObject : RealmObject() {

    @PrimaryKey
    var uuid: Long = 0 //Database ID

    //Better than just implementing a hive's ID: Realm lets us keep an actual reference to that object.
    var hiveLogged: HiveRealmObject? = null //Granted, to make Kotlin happy, I have to make it optional.
    //This might be something worth revisiting, though my guess is that it won't matter that much.

    var date: Date = Date() //Date of this object's creation (i.e date of inspection)

    var sawQueen : Boolean = false
    var sawNewEggs : Boolean = false

    var honeyFrameCount : Int = 0
    var pollenFrameCount : Int = 0
    var broodFrameCount : Int = 0

    var checkedForDroneMites : Boolean = false
    var relativeMiteCount : Int? = null //This can be null if no inspection occurred, or it can have a value if one did.

    var noteString: String = "" //A pretty crappy default, but...
}