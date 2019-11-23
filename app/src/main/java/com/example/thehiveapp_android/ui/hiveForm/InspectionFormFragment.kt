package com.example.thehiveapp_android.ui.hiveForm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveLogRealmObject
import com.example.thehiveapp_android.data.HiveRealmObject
import com.example.thehiveapp_android.ui.hive.HiveRealmListAdapter
import io.realm.RealmBaseAdapter
import io.realm.RealmResults
import java.util.*

/**
 * A fragment which manages the display information for an inspection form.
 *
 * @author idk
 */
class InspectionFormFragment : Fragment() {

    companion object {
        /**
         * Class-level method to retrieve a new instance of the class.
         *
         * @return a new HiveFormFragment.
         */
        fun newInstance() = InspectionFormFragment()
    }

    /**
     * Called to have the fragment instantiate its user interface view. This will be called between
     * onCreate(Bundle) and onActivityCreated(Bundle)
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
        val root = inflater.inflate(R.layout.inspection_form_fragment, container, false)

        val hiveNameSpinner: Spinner = root.findViewById(R.id.hiveNameSpinner)

        var hives: RealmResults<HiveRealmObject> = DataManager.instance.getAllHives()
        val hivesAdapter = HiveRealmListAdapter(requireContext(), hives, true)
        hiveNameSpinner.adapter = hivesAdapter



        var honeyInput: RadioGroup = root.findViewById(R.id.honeyInput)
        var pollenInput: RadioGroup = root.findViewById(R.id.pollenInput)
        var broodFrameCountInput: RadioGroup = root.findViewById(R.id.broodInput)
        var seenQueenSwitch: Switch = root.findViewById(R.id.seen_queen_switch)
        var seenEggsSwitch: Switch = root.findViewById(R.id.seen_eggs_switch)
        var miteCheckSwitch: Switch = root.findViewById(R.id.mite_check_switch)




        val button = root.findViewById(R.id.saveButton) as Button;
        button.setOnClickListener{
            val newLog = HiveLogRealmObject()
            var selectedHive = hiveNameSpinner.selectedItem
            Log.d("InspectionFormFragment","selectedHive = ${selectedHive}")

            newLog.uuid = UUID.randomUUID().mostSignificantBits
            newLog.honeyFrameCount = honeyInput.checkedRadioButtonId
            newLog.pollenFrameCount = pollenInput.checkedRadioButtonId
            newLog.broodFrameCount  = broodFrameCountInput.checkedRadioButtonId
            newLog.sawQueen = seenQueenSwitch.isChecked
            newLog.sawNewEggs = seenEggsSwitch.isChecked
            if(miteCheckSwitch.isChecked) {
                newLog.relativeMiteCount = 5
            }
            else {
                newLog.relativeMiteCount = 0
            }
            if(selectedHive is HiveRealmObject) {
                selectedHive.addLog(newLog)
            }

//            DataManager.instance.saveObject(newLog)
        }

        return root
    }

    /**
     * Called when the fragment's activity has been created and this fragment's view hierarchy
     * instantiated. It can be used to do final initialization once these pieces are in place, such
     * as retrieving views or restoring state. It is also useful for fragments that use
     * `setRetainInstance(boolean)` to retain their instance, as this callback tells the fragment
     * when it is fully associated with the new activity instance. This is called after
     * `onCreateView()` and before `onViewStateRestored(Bundle)`.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     *      this is the state.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
