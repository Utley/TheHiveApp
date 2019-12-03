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
 * Adapts a RealmResults object for the HiveListFragment.
 *
 * This class acts like a wrapper for a RealmResults object, providing a modifier interface that can
 * then be used in a HiveListFragment.
 *
 * @property layoutInflater the current context's layout inflater
 * @constructor creates a wrapper around the given RealmResults object with the given context
 * @author Zac
 */
class HiveRealmListAdapter(
    private val context: Context,
    private val realmResults: RealmResults<HiveRealmObject>,
    private val automaticUpdate: Boolean)
    : RealmBaseAdapter<HiveRealmObject>(realmResults), ListAdapter
{

    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    /**
     * Retrieves the number of Realm results
     *
     * @return the number of Realm results
     */
    override fun getCount(): Int {
        return realmResults.size
    }

    /**
     * Retrieves the HiveRealmObject located at the given position.
     *
     * @param position position of the desired object
     * @return the HiveRealmObject located at `position`, or null
     */
    override fun getItem(position: Int): HiveRealmObject? {
        return realmResults[position]
    }

    /**
     * Populates a view based on an element of our set of Realm results.
     *
     * Populates a view based on an element of our set of Realm results. If convertView is null, a
     * new view object will be created; otherwise, this function will attempt to fill convertView.
     *
     * @param position position of the result to retrieve
     * @param convertView a view which can be filled
     * @param parent view's grouping (??)
     * @return the filled view object
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        return if (convertView == null){
            var view = layoutInflater.inflate(R.layout.item_hive, null) as HiveListItemView

            // Warning: Force unwrap happens here! See if we can convert this to something that doesn't do that.
            view.populate(getItem(position)!!)

            // In a "return if" block like this, the last expression is returned as a value.
            view
        } else {
            (convertView as HiveListItemView).populate(getItem(position)!!)
            convertView
        }
    }

}