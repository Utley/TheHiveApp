package com.example.thehiveapp_android.ui.hive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.thehiveapp_android.R

class HiveDetail : Fragment() {

    private lateinit var viewModel: HiveListViewModel

    private lateinit var hiveNameTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.hive_detail_fragment, container, false)

        val hiveName: TextView = root.findViewById(R.id.name)
        hiveName.text = viewModel.selectedHive.name;

        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

}
