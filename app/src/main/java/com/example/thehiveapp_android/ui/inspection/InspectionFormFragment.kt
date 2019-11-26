package com.example.thehiveapp_android.ui.inspection

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Switch
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.HiveLogRealmObject
import com.example.thehiveapp_android.ui.dialog.DialogManager
import com.example.thehiveapp_android.ui.hive.HiveListViewModel
import java.util.*


class InspectionFormFragment : Fragment() {

    private lateinit var viewModel: HiveListViewModel

    companion object {
        fun newInstance() = InspectionFormFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.inspection_form_fragment, container, false)

        var honeyRadioGroup: RadioGroup = root.findViewById(R.id.honeyInput)
        var pollenRadioGroup: RadioGroup = root.findViewById(R.id.pollenInput)
        var broodRadioGroup: RadioGroup = root.findViewById(R.id.broodInput)
        var seenQueenSwitch: Switch = root.findViewById(R.id.seen_queen_switch)
        var seenEggsSwitch: Switch = root.findViewById(R.id.seen_eggs_switch)
        var miteCheckSwitch: Switch = root.findViewById(R.id.seen_mites_switch)

        val button = root.findViewById(R.id.saveButton) as Button
        button.setOnClickListener{

            val titleText = "Bzzt!"
            val messageText = "Won't you bee a honey and fill out all the (flowery) fields?"

            //checkedRadioButtonId is -1 if there is no selection.
            if (honeyRadioGroup.checkedRadioButtonId < 0 ||
                pollenRadioGroup.checkedRadioButtonId < 0 ||
                broodRadioGroup.checkedRadioButtonId < 0){
                DialogManager.instance.presentDialog(this.context, titleText, messageText)
            } else {

                val newLog = HiveLogRealmObject()
                var selectedHive = viewModel.selectedHive
                Log.d("InspectionFormFragment", "selectedHive = ${selectedHive}")

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

                selectedHive.addLog(newLog)

                activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.hive_detail)
            }
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

}
