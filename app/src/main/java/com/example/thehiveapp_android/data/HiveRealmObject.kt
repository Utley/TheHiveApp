package com.example.thehiveapp_android.data

import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

//RealmObject() descendants must be open classes; Kotlin classes are final by default
open class HiveRealmObject() : RealmObject() {
    //These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = UUID.randomUUID().mostSignificantBits
    var name: String = "My New Hive"

    private var createdAtDate: Date = Date()
    val createdAt : Date
        get() = createdAtDate

    var queenedAt: Date = Date()

    private var hiveLogsList : RealmList<HiveLogRealmObject> = RealmList<HiveLogRealmObject>()
    val hiveLogs : RealmList<HiveLogRealmObject>
        get() = hiveLogsList

    fun addLog(newLog : HiveLogRealmObject) {
        hiveLogs.add(newLog)
    }



}