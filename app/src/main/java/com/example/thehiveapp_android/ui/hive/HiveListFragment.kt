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
 * This class is responsible for managing the display of a list of hives from the Realm database and
 * listening for user input.
 *
 * @author Zac
 */
class HiveListFragment : Fragment() {
    // This class acts like a wrapper for a RealmResults object, providing a modifier interface that can
    // then be used in a HiveListItemView.

    private lateinit var viewModel: HiveListViewModel

    private lateinit var hiveListView : ListView


    /**
     * Called to have the fragment instantiate its user interface view. This will be called between
     * `onCreate(Bundle)` and `onActivityCreated(Bundle)`.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the
     * fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be
     * attached to.  The fragment should not add the view itself, but this can be used to generate
     * the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     * @return Return the View for the fragment's UI, or null.
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
     * Called to do initial creation of a fragment. This is called after `onAttach(Activity)` and
     * before `onCreateView(LayoutInflater, ViewGroup, Bundle)`.
     *
     * Note that this can be called while the fragment's activity is still in the process of being
     * created. As such, you can not rely on things like the activity's content view hierarchy being
     * initialized at this point.
     *
     * Any restored child fragments will be created before the base `Fragment.onCreate` method
     * returns.
     *
     * @param savedInstanceState If the fragment is being re-created from
     * a previous saved state, this is the state.
     * @throws Exception The specified activity is invalid
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }
}