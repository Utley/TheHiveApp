package com.example.thehiveapp_android.ui.hive

import androidx.lifecycle.ViewModel
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.RealmResults

/**
 * Internal model for a HiveListFragment.
 *
 * @author I forgot how to check
 */
class HiveListViewModel : ViewModel() {
    /**
     * Retrieves the list of hives currently stored in the database.
     */
    var hives: RealmResults<HiveRealmObject> = DataManager.instance.getAllHives()

    /**
     * The hive that is currently selected.
     */
    var selectedHive: HiveRealmObject = HiveRealmObject()
}