package com.example.thehiveapp_android

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup


/**
 * Represents a diagram of a hive.
 *
 * @author Stephen
 */
class HiveDiagram : Fragment() {

    /**
     * Do these need docs?
     *  apparently they're singleton inner classes, so I guess?
     */
    companion object {
        // what is this syntax??
        fun newInstance() = HiveDiagram()
    }

    /**
     * Class property, similar to a field; `lateinit` just means it's not defined at compile time
     */
    private lateinit var viewModel: HiveDiagramViewModel

    /**
     *
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hive_diagram_fragment, container, false)
    }

    /**
     *
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(HiveDiagramViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
