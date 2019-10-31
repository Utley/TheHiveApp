package com.example.thehiveapp_android.ui.hiveForm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import java.util.*

class HiveFormFragment : Fragment() {

    companion object {
        fun newInstance() = HiveFormFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.hive_form_fragment, container, false)

        val hiveName: EditText = root.findViewById(R.id.hiveNameTextInput)

        val button = root.findViewById(R.id.saveButton) as Button;
        button.setOnClickListener{
            Log.d("HiveFormFragment", hiveName.text.toString());
            val newHive = HiveRealmObject()
            newHive.uuid = UUID.randomUUID().mostSignificantBits
            newHive.name = hiveName.text.toString()
            DataManager.instance.saveObject(newHive)
        }

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
