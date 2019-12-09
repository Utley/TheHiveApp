package com.example.thehiveapp_android.ui.hive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.ui.inspection.InspectionAdapter
import io.realm.Sort
import java.text.DateFormat

/**
 * Fragment which handles displaying a detailed display of a hive.
 *
 * @author Cole
 */
class HiveDetailFragment : Fragment() {

    private var viewModel = HiveListViewModel.instance

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.fragment_hive_detail, container, false)

        val title: TextView = root.findViewById(R.id.title)
        title.text = viewModel.selectedHive.name

        val createdOn: TextView = root.findViewById(R.id.field_created)
        val createdDate = viewModel.selectedHive.createdAt
        createdOn.text = DateFormat.getDateTimeInstance().format(createdDate)

        viewAdapter = InspectionAdapter(
            viewModel.selectedHive.inspections.sort("date", Sort.DESCENDING),
            viewModel,
            requireActivity()
        )
        viewManager = LinearLayoutManager(activity)

        recyclerView = root.findViewById<RecyclerView>(R.id.inspections).apply {
            // Improves performance when changes in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

        // Clicking the '+' button opens the new inspection form
        val addInspectionButton = root.findViewById(R.id.add_inspection) as Button
        addInspectionButton.setOnClickListener {
            activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.navigation_inspection_form)
        }

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
