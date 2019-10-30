package com.example.thehiveapp_android.ui.hive

import android.app.Application
import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.R.id.*
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.RealmBaseAdapter

/**
 * Displays a list of hives to the user.
 *
 * Displays a list of hives to the user which the user can select to inspect specific details.
 *
 * fragment in which the user views the list of hives (view controller)
 */
class HiveListFragment : Fragment() {
    private lateinit var hiveListView : ListView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.hive_selection_list_view, container, false)

        hiveListView = root.findViewById(hive_selection_list_view)

        val hiveRealmResults = DataManager.instance.getAllHives() //RealmResults<HiveRealmObject>
        //We do get to use this like an array/list, but we also get free updates to objects we change here.
        //i.e if we change an object in this array, those changes should be reflected in the database.

        //requireContext may throw if this isn't associated with a context...
        val adapter = HiveRealmListAdapter(requireContext(), hiveRealmResults, true)
        hiveListView.adapter = adapter

        Log.d("HiveListFragment", "adapter.count == ${adapter.count}")
        Log.d("HiveListFragment", "hiveRealmResults.size == ${hiveRealmResults.size}")

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("HiveListFragment","Size of DB: ${DataManager.instance.getAllHives().size}")
        if (DataManager.instance.getAllHives().size == 0) {
            var newHive = HiveRealmObject()
            newHive.name = "Test Hive"
            DataManager.instance.saveObject(newHive)
            Log.d("HiveListFragment","Saved an object.")
        }
    }


}