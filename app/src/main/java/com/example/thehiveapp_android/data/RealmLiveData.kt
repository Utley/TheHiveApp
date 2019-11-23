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
     * Called when the number of active observers change to 1 from 0.
     *
     * This callback can be used to know that this LiveData is being used thus should be kept up to
     * date.
     */
    override fun onActive() {
        realmResults.addChangeListener(listener)
    }

    /**
     * Called when the number of active observers change from 1 to 0.
     *
     * This does not mean that there are no observers left, there may still be observers but their
     * lifecycle states aren't `Lifecycle.State.STARTED` or `Lifecycle.State.RESUMED` (like an
     * Activity in the back stack).
     *
     * You can check if there are observers via `hasObservers()`.
     */
    override fun onInactive() {
        realmResults.removeChangeListener(listener)
    }
}