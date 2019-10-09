package com.example.thehiveapp_android.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.Date

//RealmObject() descendants must be open classes; Kotlin classes are final by default
open class HiveRealmObject() : RealmObject() {
    //These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = 0
    var name: String = "My New Hive"
    var createdAt: Date = Date()

    var queenedAt: Date = Date()

    var hiveLogs : RealmList<HiveLogRealmObject> = RealmList<HiveLogRealmObject>()
}