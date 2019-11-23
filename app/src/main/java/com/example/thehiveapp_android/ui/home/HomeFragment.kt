package com.example.thehiveapp_android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import java.util.*

/**
 * Fragment to manage displaying the app home screen.
 *
 * @author I forgot how to check
 */
class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel

    /**
     * Defines behavior on object creation.
     *
     * @param inflater The LayoutInflater
     * @param container The ViewGroup containing this fragment
     * @param savedInstanceState A previously-saved instance state
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProviders.of(this).get(HomeViewModel::class.java)
        // val root = inflater.inflate(R.layout.fragment_home, container, false)
        val root = inflater.inflate(R.layout.hive_form_fragment, container, false)
        val hiveName: EditText = root.findViewById(R.id.hiveNameTextInput)
        val button = root.findViewById(R.id.saveButton) as Button
        button.setOnClickListener{
            Log.d("InspectionFormFragment", hiveName.text.toString())
            val newHive = HiveRealmObject()
            newHive.uuid = UUID.randomUUID().mostSignificantBits
            newHive.name = hiveName.text.toString()
            DataManager.instance.saveObject(newHive)
        }

        return root
    }
}