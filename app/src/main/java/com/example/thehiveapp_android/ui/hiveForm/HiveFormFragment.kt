package com.example.thehiveapp_android.ui.hiveForm

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import java.util.*

/**
 * A fragment which manages the display information for a hive form.
 *
 * @author idk
 */
class HiveFormFragment : Fragment() {

    companion object {
        /**
         * Class-level method to retrieve a new instance of the class.
         *
         * @return a new HiveFormFragment.
         */
        fun newInstance() = HiveFormFragment()
    }

    /**
     * Called to have the fragment instantiate its user interface view. This will be called between
     * `onCreate(Bundle)` and `onActivityCreated(Bundle)`.
     *
     * @param inflater The LayoutInflater object that can be used to inflate any views in the
     * fragment
     * @param container If non-null, this is the parent view that the fragment's UI should be
     * attached to.  The fragment should not add the view itself, but this can be used to generate
     * the LayoutParams of the view.
     * @param savedInstanceState If non-null, this fragment is being re-constructed from a previous
     * saved state as given here.
     * @return Return the View for the fragment's UI, or null.
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.hive_form_fragment, container, false)

        val hiveName: EditText = root.findViewById(R.id.hiveNameTextInput)

        val button = root.findViewById(R.id.saveButton) as Button;
        button.setOnClickListener{
            Log.d("HiveFormFragment", hiveName.text.toString());
            val newHive = HiveRealmObject()
            newHive.uuid = UUID.randomUUID().mostSignificantBits
            newHive.name = hiveName.text.toString()
            DataManager.instance.saveObject(newHive)
        }

        return root
    }

    /**
     * Called when the fragment's activity has been created and this fragment's view hierarchy
     * instantiated. It can be used to do final initialization once these pieces are in place, such
     * as retrieving views or restoring state. It is also useful for fragments that use
     * `setRetainInstance(boolean)` to retain their instance, as this callback tells the fragment
     * when it is fully associated with the new activity instance. This is called after
     * `onCreateView()` and before `onViewStateRestored(Bundle)`.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     *      this is the state.
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
    }

}
