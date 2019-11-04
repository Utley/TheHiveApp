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

class InspectionFormFragment : Fragment() {

    companion object {
        fun newInstance() = InspectionFormFragment()
    }


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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
