package  com.example.thehiveapp_android.ui.yard

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.thehiveapp_android.R

/**
 * Fragment which represents the beekeeper's yard.
 *
 * A Fragment object which represents the beekeeper's yard.
 *
 * @author I forgot how to check
 */
class Yard : Fragment() {

    companion object {
        /**
         * Class-level method to retrieve a new instance of the class.
         *
         * @return a new HiveFormFragment.
         */
        fun newInstance() = Yard()
    }

    private lateinit var viewModel: YardViewModel

    /**
     * Called to have the fragment instantiate its user interface view. This will be called between
     * onCreate(Bundle) and onActivityCreated(Bundle)
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
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hive_diagram_fragment, container, false)
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
        viewModel = ViewModelProviders.of(this).get(YardViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
