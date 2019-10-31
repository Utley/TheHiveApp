package com.example.thehiveapp_android.data

import android.util.Log
import androidx.lifecycle.LiveData
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
            this.saveObject(newHive)
            Log.d("HiveListFragment","Saved an object.")
        }
        else {
            Log.d("DataManager", "${hiveRealmResults[0]}")
        }

        return hiveRealmResults
    }


    /**
     * Saves the given RealmObject to the database
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

    // private constructor()
    // TODO?
}