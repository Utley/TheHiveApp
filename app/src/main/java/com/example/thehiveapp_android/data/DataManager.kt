package com.example.thehiveapp_android.data

import android.util.Log
import androidx.lifecycle.LiveData
import io.realm.Realm
import io.realm.RealmModel
import io.realm.RealmObject
import io.realm.RealmResults

class DataManager {
    //Let's make this a singleton class...
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

    //In case a generic version of this is needed.
    fun getAllObjectsOfType(classz: Class<out RealmObject>) = realm.where(classz).findAll()

    //Get all hives from the database, synchronously.
    fun getAllHives() : RealmResults<HiveRealmObject> {
        val hiveRealmResults = realm.where(HiveRealmObject::class.java).findAll()

        // TODO: Remove - used for testing with empty database
        Log.d("DataManager","Size of DB: ${hiveRealmResults.size}")
        if (hiveRealmResults.size == 0) {
            val newHive = HiveRealmObject()
            newHive.name = "Test Hive"
            this.saveObject(newHive)
            Log.d("HiveListFragment","Saved an object.")
        }
        else {
            Log.d("DataManager", "${hiveRealmResults[0]}")
        }

        return hiveRealmResults
    }

    fun getAllReminders() = realm.where(ReminderRealmObject::class.java).findAll()

    fun saveObject(saveMe: RealmObject){
        //If we make the RealmObject first,
        //we should (in theory) pass it in and then use copyToRealm to save it to the database.
        realm.executeTransactionAsync { realm ->
            realm.copyToRealm(saveMe)
        }
        //If an object has already been fetched from Realm, generally, we shouldn't need to use this
        //method as Realm will handle the changes we made (TODO: test to make sure this is true)
    }

    fun deleteObject(deleteMe: RealmObject){
        //Similar to the method above; async block in which we call a delete operation.
        realm.executeTransactionAsync { _ ->
            deleteMe.deleteFromRealm()
        }
    }

    fun deleteObjectsInRealmResults(deleteUs: RealmResults<Any>){
        //Similar, again, to the method above; pass in a RealmResults containing all the objects
        //we want to delete, and we'll delete them all.
        realm.executeTransactionAsync { _ ->
            deleteUs.deleteAllFromRealm()
        }
    }



}