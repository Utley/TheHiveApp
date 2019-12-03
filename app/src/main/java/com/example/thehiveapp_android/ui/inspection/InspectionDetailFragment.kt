package com.example.thehiveapp_android.ui.inspection

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.ui.hive.HiveListViewModel
import java.text.DateFormat

/**
 * Fragment to manage displaying an inspection detail screen.
 *
 * @author Cole ??
 */
class InspectionDetailFragment : Fragment() {
    private lateinit var viewModel: HiveListViewModel

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
        val root = inflater.inflate(R.layout.fragment_inspection_detail, container, false)

        val dateView: TextView = root.findViewById(R.id.date)
        val inspectionDate = viewModel.selectedInspection.date
        dateView.text = DateFormat.getDateTimeInstance().format(inspectionDate)

        val honeyCount: TextView = root.findViewById(R.id.honeyCount)
        honeyCount.text = viewModel.selectedInspection.honeyFrameCount.toString()

        val broodCount: TextView = root.findViewById(R.id.broodCount)
        broodCount.text = viewModel.selectedInspection.broodFrameCount.toString()

        val pollenCount: TextView = root.findViewById(R.id.pollenCount)
        pollenCount.text = viewModel.selectedInspection.pollenFrameCount.toString()

        val eggCheckbox: CheckBox = root.findViewById(R.id.eggCheckbox)
        eggCheckbox.isChecked = viewModel.selectedInspection.sawNewEggs

        val miteCheckbox: CheckBox = root.findViewById(R.id.miteCheckbox)
        miteCheckbox.isChecked = viewModel.selectedInspection.checkedForDroneMites

        val queenCheckbox: CheckBox = root.findViewById(R.id.queenCheckbox)
        queenCheckbox.isChecked = viewModel.selectedInspection.sawQueen


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
