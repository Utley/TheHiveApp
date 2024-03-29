package com.example.thehiveapp_android.ui.hive

import androidx.lifecycle.ViewModel
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.InspectionRealmObject
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.RealmResults

/**
 * Internal model for a HiveListFragment.
 *
 * This class acts as an internal model for a HiveListFragment, tracking all the variables needed to
 * monitor the fragment's state. It acts similar to a C struct, only serving to group a set of
 * related mutable variables.
 *
 * @property hives list of hives currently stored in the database
 * @property selectedHive hive that is currently selected
 * @property selectedInspection log we're currently inspecting
 * @author I forgot how to check
 */
class HiveListViewModel : ViewModel() {

    companion object {
        private var modelInstance : HiveListViewModel? = null

        val instance: HiveListViewModel
            get() {
                if (modelInstance == null){
                    modelInstance = HiveListViewModel()
                }
                return modelInstance!!
            }
    }

    /**
     * Retrieves the list of hives currently stored in the database.
     */
    var hives: RealmResults<HiveRealmObject> = DataManager.instance.getAllHives()

    /**
     * The hive that is currently selected.
     */
    var selectedHive: HiveRealmObject = DataManager.instance.getAllHives()[0] ?: HiveRealmObject()

    /**
     * The log we're currently inspecting.
     */
    var selectedInspection: InspectionRealmObject = InspectionRealmObject()

}
