package com.example.thehiveapp_android.data

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey

open class YardHiveRealmObject() : RealmObject() {
    //These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = 0

    //The hive object this yardhive represents.
    var hiveObject: HiveRealmObject? = null

    //x and y coordinates.
    var x: Int = 0
    var y: Int = 0

}