package com.example.thehiveapp_android.data

import io.realm.Realm
import io.realm.RealmObject

class DataManager {
    //Let's make this a singleton class...
    companion object {
        private lateinit var managerInstance : DataManager

        val instance: DataManager
            get() {
                if (managerInstance == null){
                    managerInstance = DataManager()
                }
                return managerInstance
            }

    }

    private var realm : Realm = Realm.getDefaultInstance()


    fun saveObject(saveMe: RealmObject){

        //If we make the RealmObject first,
        //we should (in theory) pass it in and then use copyToRealm to save it to the database.
        realm.executeTransactionAsync { realm ->
            realm.copyToRealm()
        }
    }

    // private constructor()


}