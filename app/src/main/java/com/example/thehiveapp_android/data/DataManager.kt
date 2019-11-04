package com.example.thehiveapp_android.data

import android.util.Log
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults

/**
 * Manages the data stored in a Realm database.
 *
 * Singleton class which provides a common interface to access or modify the data stored in the
 * app's Realm database.
 *
 * @author Zac
 */
class DataManager {
    // Let's make this a singleton class...
    companion object {
        private var managerInstance : DataManager? = null

        val instance: DataManager
            get() {
                if (managerInstance == null){
                    managerInstance = DataManager()
                }
                return managerInstance!!
            }
    }

    private var realm : Realm = Realm.getDefaultInstance()

    private fun <T: RealmModel> RealmResults<T>.asLiveData() = RealmLiveData(this)

    
    /**
     * Synchronously retrieves all objects of a given type from the database.
     *
     * Synchronously retrieves all Realm object of a given type from the database.
     *
     * @param classz A class representing the object type to retrieve
     * @return a list(?) of object sof the given type
     */
     //In case a generic version of this is needed.
    fun getAllObjectsOfType(classz: Class<out RealmObject>) = realm.where(classz).findAll()

    /**
     * Synchronously retrieves all hives from the database.
     *
     * Synchronously retrieves all hives from the database. If no hives have been entered, creates
     * a dummy object and adds that; I assuuuuume that's just debug behavior?
     *
     * @return a list(?) of HiveRealmObjects
     */
    fun getAllHives() : RealmResults<HiveRealmObject> {
        val hiveRealmResults = realm.where(HiveRealmObject::class.java).findAll()

        // TODO: Remove - used for testing with empty database
        Log.d("DataManager","Size of DB: ${hiveRealmResults.size}")
        if (hiveRealmResults.size == 0) {
            val newHive = HiveRealmObject()
            newHive.name = "Test Hive"
            val inspection1 = HiveLogRealmObject()
            val inspection2 = HiveLogRealmObject()
            val inspection3 = HiveLogRealmObject()
            newHive.addLog(inspection1)
            newHive.addLog(inspection2)
            newHive.addLog(inspection3)
            this.saveObject(newHive)
            Log.d("HiveListFragment","Saved an object.")
        }
        else {
            Log.d("DataManager", "${hiveRealmResults[0]}")
        }

        return hiveRealmResults
    }

    /**
     * Retrieves all reminders from the database.
     *
     * Retrieves all reminder objects from the database. 
     *
     * @return a list(?) of reminder objects
     */
    fun getAllReminders() = realm.where(ReminderRealmObject::class.java).findAll()

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
        // If an object has already been fetched from Realm, generally, we shouldn't need to use this
        // method as Realm will handle the changes we made (TODO: test to make sure this is true)
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
     * Removes a series of RealmObject from the database.
     *
     * Asynchronously removes all objects in the given RealmResults set from the database.
     *
     * @param deleteMe RealmObject to delete
     */
    fun deleteObjectsInRealmResults(deleteUs: RealmResults<Any>){
        // Similar, again, to the method above; pass in a RealmResults containing all the objects
        // we want to delete, and we'll delete them all.
        realm.executeTransactionAsync { _ ->
            deleteUs.deleteAllFromRealm()
        }
    }

}