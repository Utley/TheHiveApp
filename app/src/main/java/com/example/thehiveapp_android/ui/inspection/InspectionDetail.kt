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

class InspectionDetail : Fragment() {
    private lateinit var viewModel: HiveListViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_inspection_detail, container, false)

        val date: TextView = root.findViewById(R.id.date)
        date.text = viewModel.selectedInspection.date.toString()

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }
}
