package com.example.thehiveapp_android.ui.yard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thehiveapp_android.R


class Yard : Fragment() {

    companion object {
        fun newInstance() = Yard()
    }

    private lateinit var viewModel: YardViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hive_diagram_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(YardViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
