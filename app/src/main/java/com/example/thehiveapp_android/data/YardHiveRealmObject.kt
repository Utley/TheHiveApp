package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

/**
 * Represents a hive's status within a yard.
 *
 * Stores a Realm representation of a hive's status within the yard. This object acts like a struct
 * in C, serving only to group public fields in one place.
 *
 * @property uuid The hive location's database ID number
 * @property hiveObject The hive this object corresponds to
 * @property x x coordinate
 * @property y y coordinate
 * @author Zac
 */
// linter says they're never used. Given that HiveRealmObject has its own coordinate fields, I
// believe it
/*
open class YardHiveRealmObject : RealmObject() {
    // These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = 0

    // The hive object this yard hive represents.
    var hiveObject: HiveRealmObject? = null

    // x and y coordinates.
    var x: Int = 0
    var y: Int = 0
}
*/