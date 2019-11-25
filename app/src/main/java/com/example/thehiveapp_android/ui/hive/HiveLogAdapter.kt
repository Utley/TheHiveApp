package com.example.thehiveapp_android.ui.hive

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.HiveLogRealmObject
import io.realm.RealmList


class HiveLogAdapter(private val hiveLogs: RealmList<HiveLogRealmObject>,
                     private val viewModel: HiveListViewModel,
                     private val context: FragmentActivity):
    RecyclerView.Adapter<HiveLogAdapter.HiveLogViewHolder>() {
    class HiveLogViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView)

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HiveLogAdapter.HiveLogViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hive_log_list_item, parent, false) as LinearLayout

        return HiveLogViewHolder(textView)
    }

    override fun onBindViewHolder(holder: HiveLogViewHolder, position: Int) {
        val inspectionDate = holder.textView.findViewById<TextView>(R.id.inspection_date)
        inspectionDate.text = hiveLogs[position]?.date.toString()

        holder.textView.setOnClickListener {
            // TODO: Start animation - make the selected row darker
            val selectedInspection = viewModel.selectedHive.hiveLogs.get(position)
            if (selectedInspection != null) {
                viewModel.selectedInspection = selectedInspection
            }
            context.findNavController(R.id.nav_host_fragment).navigate(R.id.inspection_detail)

        }
    }

    override fun getItemCount() = hiveLogs.size
}