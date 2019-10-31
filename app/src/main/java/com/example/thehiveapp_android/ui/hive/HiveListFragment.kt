package com.example.thehiveapp_android.ui.hive

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.R.id.hive_selection_list_view
import com.example.thehiveapp_android.R.id.nav_host_fragment

/**
 * Adapts a RealmResults object for the HiveListItemView.
 *
 * This class acts like a wrapper for a RealmResults object, providing a modifier interface that can
 * then be used in a HiveListItemView.
 *
 * @author ???
 */
class HiveListFragment : Fragment() {

    private lateinit var viewModel: HiveListViewModel

    private lateinit var hiveListView : ListView


    /**
     * Creates a new view object.
     *
     * @param inflater
     * @param container the ViewGroup this view belongs to ???
     * @param savedInstanceState
     * @return an inflated layout
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.hive_selection_list_view, container, false)

        hiveListView = root.findViewById(hive_selection_list_view)

        hiveListView.setOnItemClickListener { parent, view, position, id ->
            val selectedHive = viewModel.hives.get(position)
            if (selectedHive != null) {
                viewModel.selectedHive = selectedHive
            }
            activity?.findNavController(nav_host_fragment)?.navigate(R.id.hive_detail)
        }

        // For hiveRealmResults, we do get to use this like an array/list, but we also get free updates to objects we change here.
        // i.e if we change an object in this array, those changes should be reflected in the database.
        val hiveRealmResults = viewModel.hives

        //requireContext may throw if this isn't associated with a context...
        val adapter = HiveRealmListAdapter(requireContext(), hiveRealmResults, true)
        hiveListView.adapter = adapter
        Log.d("HiveListFragment", "adapter.count == ${adapter.count}")
        Log.d("HiveListFragment", "hiveRealmResults.size == ${hiveRealmResults.size}")

        return root
    }

    /**
     * Specifies the object's behavior on creation.
     *
     * Specifies the object's behavior on creation.
     *
     * @throws Exception The specified activity is invalid
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }
}