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

/**
 * Adapter used to view hive logs in some context.
 *
 * I don't actually entirely know what's going on here, need to verify with whoever wrote this. dang
 * kotlin and it's weird optimized syntax
 *
 * @param hiveLogs Hive logs being analyzed
 * @param viewModel The internal model we're working off
 * @param context The current activity context
 * @constructor ???
 * @author still don't know how to check =/
 */
class HiveLogAdapter(private val hiveLogs: RealmList<HiveLogRealmObject>,
                     private val viewModel: HiveListViewModel,
                     private val context: FragmentActivity):
    RecyclerView.Adapter<HiveLogAdapter.HiveLogViewHolder>() {

    // private... class? With no members? what???
    // apparently we return it... so I guess it's just a rename for abstraction's sake?
    class HiveLogViewHolder(val textView: LinearLayout) : RecyclerView.ViewHolder(textView)

    /**
     * Called when `RecyclerView` needs a new `ViewHolder` of the given type to represent an item.
     *
     * This new `ViewHolder` should be constructed with a new `View` that can represent the items
     * of the given type. You can either create a new `View` manually or inflate it from an XML
     * layout file.
     *
     * The new `ViewHolder` will be used to display items of the adapter using
     * `onBindViewHolder(ViewHolder, int, List)`. Since it will be re-used to display different
     * items in the data set, it is a good idea to cache references to sub views of the
     * `View` to avoid unnecessary `View.findViewById(int)` calls.
     *
     * @param parent The `ViewGroup` into which the new `View` will be added after it is bound to an
     *      adapter position
     * @param viewType The view type of the new `View`
     * @return A new `ViewHolder` that holds a `View` of the given view type
     */
    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): HiveLogViewHolder {
        val textView = LayoutInflater.from(parent.context)
            .inflate(R.layout.hive_log_list_item, parent, false) as LinearLayout

        return HiveLogViewHolder(textView)
    }

    /**
     * Called by `RecyclerView` to display the data at the specified position. This method should
     * update the contents of the `ViewHolder.itemView` to reflect the item at the given position.
     *
     * Note that unlike `android.widget.ListView`, `RecyclerView` will not call this method again if
     * the position of the item changes in the data set unless the item itself is invalidated or the
     * new position cannot be determined. For this reason, you should only use the `position`
     * parameter while acquiring the related data item inside this method and should not keep a copy
     * of it. If you need the position of an item later on (e.g. in a click listener), use
     * `ViewHolder.getAdapterPosition()` which will have the updated adapter position.
     *
     * @param holder The ViewHolder which should be updated to represent the contents of the
     *        item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
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

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount() = hiveLogs.size
}