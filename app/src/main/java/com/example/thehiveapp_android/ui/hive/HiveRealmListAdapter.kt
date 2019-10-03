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
        if (convertView == null){
            var view = layoutInflater.inflate(R.layout.hive_selection_list_item, null)

        } else {

        }
        return View(context)
    }

}