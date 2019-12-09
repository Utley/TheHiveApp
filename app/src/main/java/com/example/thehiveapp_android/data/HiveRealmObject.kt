package com.example.thehiveapp_android.data

import io.realm.Realm
import io.realm.RealmList
import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*

/**
 * Represents a single hive.
 *
 * Stores a Realm representation of a single hive. This object acts like a struct in C, serving only
 * to group public fields in one place.
 *
 * @property uuid The hive's database ID number
 * @property name the hive's name
 * @property createdAt Date the hive was created
 * @property queenedAt Date the current queen was bought/born, roughly
 * @property inspections List of objects representing this hive's inspection logs
 * @property xPosition Hive's x coordinate position on the yard diagram
 * @property yPosition Hive's y coordinate position on the yard diagram
 * @author Zac
 */
//RealmObject() descendants must be open classes; Kotlin classes are final by default
open class HiveRealmObject : RealmObject() {
    //These do actually need default values, even if they're optional...
    @PrimaryKey
    var uuid: Long = UUID.randomUUID().mostSignificantBits
    var name: String = "My New Hive"

    private var createdAtDate: Date = Date()
    val createdAt : Date
        get() = createdAtDate

    var queenedAt: Date = Date()

    private var inspectionList : RealmList<InspectionRealmObject> = RealmList()
    val inspections : RealmList<InspectionRealmObject>
        get() = inspectionList

    var xPosition: Int = 0
    var yPosition: Int = 0

    /**
     * Adds a new hive inspection log to this hive's list.
     *
     * @param newInspection Inspection log to add
     */
    fun addInspection(newInspection : InspectionRealmObject) {
        val realm: Realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        inspections.add(newInspection)
        realm.commitTransaction()
    }

    /**
     * Removes a hive inspection log to this hive's list.
     *
     * @param newInspection Inspection log to remove
     */
    fun deleteInspection(inspectionToDelete: InspectionRealmObject) {
        val realm: Realm = Realm.getDefaultInstance()
        realm.beginTransaction()
        inspections.remove(inspectionToDelete)
        realm.commitTransaction()
    }
}