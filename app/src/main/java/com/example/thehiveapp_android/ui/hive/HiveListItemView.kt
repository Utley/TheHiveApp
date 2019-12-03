package com.example.thehiveapp_android.ui.hive

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import android.widget.TextView
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.HiveRealmObject
import java.text.DateFormat

/**
 * The layout for an individual hive item in hive in the HiveListFragment list view.
 */
class HiveListItemView : LinearLayout {
    private lateinit var hiveNameTextView: TextView
    private lateinit var hiveLastInspectedAtTextView: TextView


    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet, defaultAttrsId: Int) : super(context, attrs, defaultAttrsId)

    /**
     * Populates this object with hive information.
     *
     * Populates this object with the information for a single hive, including filling the appropriate views.
     */
    fun populate(hive: HiveRealmObject){
        hiveNameTextView = this.findViewById(R.id.inspections) as TextView
        hiveLastInspectedAtTextView = this.findViewById(R.id.hive_last_inspected_text) as TextView

        hiveNameTextView.text = hive.name

        var hiveDateString = "Not Yet Inspected"
        if (hive.inspections.size > 0) {
            val lastInspectedDate = hive.inspections.last()!!.date
            hiveDateString = DateFormat.getDateInstance().format(lastInspectedDate)
        }

        hiveLastInspectedAtTextView.text = hiveDateString
    }
}