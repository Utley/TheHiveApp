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
        fun newInstance() = Yard()
    }

    private lateinit var viewModel: YardViewModel

    /**
     * Defines behavior on object creation.
     *
     * @param inflater The LayoutInflater
     * @param container The ViewGroup containing this fragment
     * @param savedInstanceState A previously-saved instance state
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.hive_diagram_fragment, container, false)
    }

    /**
     * Defines behavior on activity creation.
     *
     * @param savedInstanceState A previously-saved instance state
     */
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(YardViewModel::class.java)
        // TODO: Use the ViewModel
    }
}
