package com.example.thehiveapp_android.ui.hive

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListAdapter
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.RealmBaseAdapter
import io.realm.RealmResults

/**
 * Adapts a RealmResults object it;s given into something the HiveListItemView can understand
 * data source for the list
 * Acts like a wrapper
 */
class HiveRealmListAdapter(
    private val context: Context,
    private val realmResults: RealmResults<HiveRealmObject>,
    private val automaticUpdate: Boolean)
    : RealmBaseAdapter<HiveRealmObject>(realmResults), ListAdapter {

    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    override fun getCount(): Int {
        return realmResults.size
    }

    override fun getItem(position: Int): HiveRealmObject? {
        return realmResults[position]
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return if (convertView == null){
            var view = layoutInflater.inflate(R.layout.hive_selection_list_item, null) as HiveListItemView
            view.populate(getItem(position)!!) //Warning: Force unwrap happens here! See if we can convert this to something that doesn't do that.
            view //In a block like this "return if", the last expression is returned as a value.
        } else {
            (convertView as HiveListItemView).populate(getItem(position)!!)
            convertView
        }
    }

}