package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Stores a single hive log.
 *
 * Stores a Realm representation of a single inspection log for a specific hive. This object acts
 * like a struct in C, serving only to group public fields in one place.
 *
 * @property uuid The Log's database ID number
 * @property hiveLogged Hive that is the target of the log
 * @property date Date of inspection
 * @property sawQueen Whether or not the beekeeper saw the queen
 * @property sawNewEggs Whether or not the beekeeper saw new eggs
 * @property honeyFrameCount Number of frames dedicated to honey production
 * @property pollenFrameCount Number of frames dedicated to storing pollen
 * @property broodFrameCount Number of frames dedicated to raising brood
 * @property checkedForDroneMites Whether or not the beekeeper checked for mites on the drones
 * @property relativeMiteCount How many mites were present, relatively; can be null if no inspection
 * @property noteString String containing an optional note
 * @author Zac
 */
open class InspectionRealmObject : RealmObject() {

    @PrimaryKey
    var uuid: Long = UUID.randomUUID().mostSignificantBits  //Database ID

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