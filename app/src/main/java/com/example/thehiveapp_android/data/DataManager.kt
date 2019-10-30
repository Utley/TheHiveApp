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

    fun saveObject(saveMe: RealmObject){

        //If we make the RealmObject first,
        //we should (in theory) pass it in and then use copyToRealm to save it to the database.
        realm.executeTransactionAsync { realm ->
            realm.copyToRealm(saveMe)
        }
    }

    // private constructor()

}