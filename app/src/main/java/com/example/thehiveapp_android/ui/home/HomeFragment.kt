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