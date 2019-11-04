package com.example.thehiveapp_android.ui.hive

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.HiveLogRealmObject
import io.realm.RealmList

class HiveLogAdapter(private val hiveLogs: RealmList<HiveLogRealmObject>):
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
    }

    override fun getItemCount() = hiveLogs.size
}