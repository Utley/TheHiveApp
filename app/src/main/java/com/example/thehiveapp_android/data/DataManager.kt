package com.example.thehiveapp_android.data

import android.util.Log
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults
import java.lang.Exception

/**
 * Manages the data stored in a Realm database.
 *
 * Singleton class which provides a common interface to access or modify the data stored in the
 * app's Realm database.
 *
 * @author Zac
 */
class DataManager private constructor (internal var realm : Realm) {
    // Let's make this a singleton class...
    companion object {
        private var managerInstance : DataManager? = null

        /**
         * Retrieves the single DataManager instance.
         *
         * Class-level function that retrieves the DataManager singleton. If a Realm object is
         * supplied for the argument and DataManager has not been instantiated, said provided Realm
         * object will be used for the DataManager instance. If no Realm object already exists, this
         * function will use the default one. If an instance already exists, that will be returned
         * and the Realm object will be ignored.
         *
         * NOTE: this function has been implemented purely for testing purposes, so we can use
         * custom databases without affecting the primary one. It should probably be reverted, or we
         * should find some other workaround; in a non-debugging environment, DataManager should
         * always use the default Realm instance.
         *
         * @param targetRealm The Realm object to use to create the DataManager instance, if one
         *  does not already exist. If this is null or if an instance already exists, it is ignored
         * @return The singleton DataManager instance
         */
        fun getInstance(targetRealm : Realm? = null) : DataManager {
            if (managerInstance == null) {
                managerInstance = DataManager(targetRealm ?: Realm.getDefaultInstance())
            }
            return managerInstance!!
        }
    }


    /**
     * Synchronously retrieves all hive logs from the database.
     *
     * @return a RealmResults containing all current hive logs
     */
    fun getAllHiveLogs() : RealmResults<InspectionRealmObject> =
        realm.where(InspectionRealmObject::class.java).findAll()

    /**
     * Synchronously retrieves all hive objects from the database.
     *
     * @return a RealmResults containing all current hives
     */
    fun getAllHives() : RealmResults<HiveRealmObject> {
        val hiveRealmResults = realm.where(HiveRealmObject::class.java).findAll()

        Log.d("DataManager","Size of DB: ${hiveRealmResults.size}")
        if (hiveRealmResults.size != 0) {
            Log.d("DataManager", "${hiveRealmResults[0]}")
        }

        return hiveRealmResults
    }

    /**
     * Saves the given RealmObject to the database.
     *
     * @param saveMe RealmObject to save
     */
    fun saveObject(saveMe: RealmObject){
        // If we make the RealmObject first,
        // we should (in theory) pass it in and then use copyToRealm to save it to the database.
        realm.executeTransactionAsync { realm ->
            realm.copyToRealm(saveMe)
        }
    }

    /**
     * Removes the given RealmObject from the database.
     *
     * Asynchronously removes the given RealmObject from the database.
     *
     * @param deleteMe RealmObject to delete
     */
    fun deleteObject(deleteMe: RealmObject){
        // Similar to the method above; async block in which we call a delete operation.
        realm.executeTransactionAsync { _ ->
            deleteMe.deleteFromRealm()
        }
    }

    /**
     * Removes a series of RealmObjects from the database.
     *
     * Asynchronously removes all objects in the given RealmResults set from the database.
     *
     * @param deleteUs RealmResults containing objects to delete
     */
    fun deleteObjectsInRealmResults(deleteUs: RealmResults<out RealmObject>) {
        // Similar, again, to the method above; pass in a RealmResults containing all the objects
        // we want to delete, and we'll delete them all.
        realm.executeTransactionAsync { _ ->
            deleteUs.deleteAllFromRealm()
        }
    }

    /**
     * Performs cleanup for database operations.
     *
     * Performs cleanup for database operations, such as closing the connections. After this method
     * is called, the current DataManager instance will be rendered invalid and cannot be used. This
     * also resets the class's instance tracking, meaning the next call to getInstance will return a
     * new valid DataManager instance.
     *
     *
     * Note: I have no idea if this is even strictly necessary. I noticed that the online test cases
     * I was looking at explicitly closed the connection and figured that was probably true for us
     * too, but this is another thing which might not be true for the normal use case. Might remove
     * this after testing or something, not sure.
     *
     * @author David
     */
    fun tearDown() {
        realm.close()
        managerInstance = null
    }
}