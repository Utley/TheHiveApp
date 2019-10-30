package com.example.thehiveapp_android.data

import io.realm.Realm
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

    //Get all hives from the database, synchronously.
    fun getAllHives() : RealmResults<HiveRealmObject> {
        return realm.where(HiveRealmObject::class.java).findAll()
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