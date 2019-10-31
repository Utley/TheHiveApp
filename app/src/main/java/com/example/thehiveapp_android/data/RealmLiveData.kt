package com.example.thehiveapp_android.data

import androidx.lifecycle.LiveData
import io.realm.RealmChangeListener
import io.realm.RealmModel
import io.realm.RealmResults

/**
 * Represents live data from the Realm database.
 *
 * double check on what this is???
 *
 * @param T The RealmModel we're tracking
 * @property realmResults Results we're tracking
 * @author Zac
 */
class RealmLiveData <T: RealmModel> (val realmResults: RealmResults<T>) : LiveData<RealmResults<T>>() {

    private val listener = RealmChangeListener<RealmResults<T>> { results -> value = results }

    /**
     * Sets the object to start listening for changes in the data.
     */
    override fun onActive() {
        realmResults.addChangeListener(listener)
    }

    /**
     * Sets the object to stop listening for changes in the data.
     */
    override fun onInactive() {
        realmResults.removeChangeListener(listener)
    }
}