package com.example.thehiveapp_android.ui.hiveForm

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView

import com.example.thehiveapp_android.R

class HiveFormFragment : Fragment() {

    companion object {
        fun newInstance() = HiveFormFragment()
    }

    private lateinit var viewModel: HiveFormViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.hive_form_fragment, container, false)

//        val textView: TextView = root.findViewById(R.id.hive_form_title_default)
        val textView: EditText = root.findViewById(R.id.hiveNameTextInput)
        textView.setText("hive 1")

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HiveFormViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
