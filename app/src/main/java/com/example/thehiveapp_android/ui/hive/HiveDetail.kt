package com.example.thehiveapp_android.ui.hive

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.thehiveapp_android.R

/**
 * Fragment which handles displaying a detailed display of a hive.
 *
 * @author I forgot how to check
 */
class HiveDetail : Fragment() {

    private lateinit var viewModel: HiveListViewModel

    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var root = inflater.inflate(R.layout.hive_detail_fragment, container, false)

        val title: TextView = root.findViewById(R.id.title)
        title.text = viewModel.selectedHive.name

        val createdOn: TextView = root.findViewById(R.id.field_created)
        createdOn.text = viewModel.selectedHive.createdAt.toString()

        viewAdapter = HiveLogAdapter(viewModel.selectedHive.hiveLogs)
        viewManager = LinearLayoutManager(activity)

        recyclerView = root.findViewById<RecyclerView>(R.id.hive_name_text).apply {
            // improves performance when changes in content do not change the layout size of the RecyclerView
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }


        return root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
    }

}
