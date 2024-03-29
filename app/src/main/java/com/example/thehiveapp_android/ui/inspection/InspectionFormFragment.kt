package com.example.thehiveapp_android.ui.inspection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.InspectionRealmObject
import com.example.thehiveapp_android.ui.dialog.DialogManager
import com.example.thehiveapp_android.ui.hive.HiveListViewModel
import java.util.*

/**
 * Fragment to manage displaying an inspection form screen.
 *
 * @author Cole
 */
class InspectionFormFragment : Fragment() {

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
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_inspection_form, container, false)

        val honeyRadioGroup: RadioGroup = root.findViewById(R.id.honeyInput)
        val pollenRadioGroup: RadioGroup = root.findViewById(R.id.pollenInput)
        val broodRadioGroup: RadioGroup = root.findViewById(R.id.broodInput)
        val seenQueenSwitch: Switch = root.findViewById(R.id.seen_queen_switch)
        val seenEggsSwitch: Switch = root.findViewById(R.id.seen_eggs_switch)
        val miteCheckSwitch: Switch = root.findViewById(R.id.seen_mites_switch)
        val notes: TextView = root.findViewById(R.id.notesInput)

        val button = root.findViewById(R.id.saveButton) as Button
        button.setOnClickListener{

            val titleText = "Bzzt!"
            val messageText = "Won't you bee a honey and fill out all the (flowery) fields?"

            // checkedRadioButtonId is -1 if there is no selection.
            if (honeyRadioGroup.checkedRadioButtonId < 0 ||
                pollenRadioGroup.checkedRadioButtonId < 0 ||
                broodRadioGroup.checkedRadioButtonId < 0){
                DialogManager.presentDialog(this.context, titleText, messageText)
            } else {

                val newLog = InspectionRealmObject()
                val selectedHive = viewModel.selectedHive
                Log.d("InspectionFormFragment", "selectedHive = $selectedHive")

                newLog.uuid = UUID.randomUUID().mostSignificantBits

                // Want the index of the radio button in its group
                var radioButtonID = honeyRadioGroup.checkedRadioButtonId
                var radioButton: RadioButton = honeyRadioGroup.findViewById(radioButtonID)
                val honeyCount = honeyRadioGroup.indexOfChild(radioButton) + 1
                newLog.honeyFrameCount = honeyCount

                radioButtonID = pollenRadioGroup.checkedRadioButtonId
                radioButton = pollenRadioGroup.findViewById(radioButtonID)
                val pollenCount = pollenRadioGroup.indexOfChild(radioButton) + 1
                newLog.pollenFrameCount = pollenCount

                radioButtonID = broodRadioGroup.checkedRadioButtonId
                radioButton = broodRadioGroup.findViewById(radioButtonID)
                val broodCount = broodRadioGroup.indexOfChild(radioButton) + 1
                newLog.broodFrameCount = broodCount

                newLog.sawQueen = seenQueenSwitch.isChecked
                newLog.sawNewEggs = seenEggsSwitch.isChecked
                newLog.checkedForDroneMites = miteCheckSwitch.isChecked

                if (miteCheckSwitch.isChecked) {
                    newLog.relativeMiteCount = 5
                } else {
                    newLog.relativeMiteCount = 0
                }

                newLog.noteString = notes.text.toString()

                selectedHive.addInspection(newLog)

                activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.navigation_hive_detail)
            }
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
     * @throws Exception when the specified activity is invalid
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

}
