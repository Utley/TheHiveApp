package com.example.thehiveapp_android.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import android.widget.Spinner;
import com.example.thehiveapp_android.ui.dialog.DialogManager

import java.util.*

/**
 * Fragment to manage displaying the app home screen.
 *
 * @author I forgot how to check
 */
class HomeFragment : Fragment() {

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
        // val root = inflater.inflate(R.layout.fragment_home, container, false)
        val root = inflater.inflate(R.layout.fragment_hive_form, container, false)
        val hiveName: EditText = root.findViewById(R.id.nameInput)
        // Create an ArrayAdapter using the string array and a default spinner layout
        val hiveSizeSpinner : Spinner = root.findViewById(R.id.hiveSizeInput)
        ArrayAdapter.createFromResource(
            this.getContext()!!,
            R.array.frame_size_array,
            android.R.layout.simple_spinner_item // default layout
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            hiveSizeSpinner.adapter = adapter
        }
        val button = root.findViewById(R.id.saveButton) as Button
        button.setOnClickListener{
            Log.d("InspectionFormFragment", hiveName.text.toString())

            val titleText = "Bzzt!"
            val messageText = "Won't you bee a honey and fill out all the (flowery) fields?"

            if (hiveName.text == null || hiveName.text.toString() == ""){
                DialogManager.instance.presentDialog(this.context, titleText, messageText)
            } else {
                val newHive = HiveRealmObject()
                newHive.uuid = UUID.randomUUID().mostSignificantBits
                newHive.name = hiveName.text.toString()
                DataManager.instance.saveObject(newHive)
                activity?.findNavController(R.id.nav_host_fragment)
                    ?.navigate(R.id.navigation_hive_list)
            }
        }

        return root
    }
}