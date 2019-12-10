package com.example.thehiveapp_android.ui.yard

import android.annotation.SuppressLint
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController

import com.example.thehiveapp_android.R

import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import com.example.thehiveapp_android.ui.hive.HiveListViewModel
import io.realm.Realm
import io.realm.RealmResults
import kotlin.math.absoluteValue
import kotlin.math.roundToInt



private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass managing the UI details for the yard screen.
 *
 * A simple [Fragment] subclass managing the UI details for the yard screen. Activities that contain
 * this fragment must implement the [YardView.OnFragmentInteractionListener] interface to handle
 * interaction events. Use the [YardView.newInstance] factory method to create an instance of this
 * fragment.
 *
 * @author Stephen
 */
class YardView : Fragment() {
    // TODO: Rename and change types of parameters
    //private var param1: String? = null
    //private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null


    private var viewModel = HiveListViewModel.instance

    /**
     * Called to do initial creation of a fragment. This is called after `onAttach(Activity)` and
     * before `onCreateView(LayoutInflater, ViewGroup, Bundle)`.
     *
     * Note that this can be called while the fragment's activity is still in the process of being
     * created. As such, you can not rely on things like the activity's content view hierarchy being
     * initialized at this point.
     *
     * Any restored child fragments will be created before the base `Fragment.onCreate` method
     * returns.
     *
     * @param savedInstanceState If the fragment is being re-created from a previous saved state,
     *  this is the state.
     * @throws Exception if the specified activity is invalid
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = activity?.run {
            ViewModelProviders.of(this)[HiveListViewModel::class.java]
        } ?: throw Exception("Invalid Activity")
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
    @SuppressLint("ClickableViewAccessibility")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = HiveListViewModel()


        val buttonWidth = 300
        val buttonHeight = 300
        val root = inflater.inflate(R.layout.fragment_yard_view, container, false)
        val allHives: RealmResults<HiveRealmObject> = DataManager.instance.getAllHives()
        val myButtons = ArrayList<Button>()
        val hiveLayout: RelativeLayout = root.findViewById(R.id.hiveContainer)

        viewModel.selectedHive = allHives[0] ?: HiveRealmObject()

        for (i in 0 until allHives.size) {
            val realmObj: HiveRealmObject = allHives[i]!!
            val button = YardButton(this.context, realmObj)
            button.setBackgroundColor(resources.getColor(R.color.colorPrimary))
            button.text = realmObj.name
            button.width = 50
            button.height = 50

            val columnCount = 3
            val params: RelativeLayout.LayoutParams  = RelativeLayout.LayoutParams(buttonWidth, buttonHeight)
            if((realmObj.xPosition == 0) and (realmObj.yPosition == 0)) {
                params.leftMargin = buttonWidth * (i % columnCount) + 10
                params.topMargin = buttonHeight * (i / columnCount) + 10
            }
            else {
                params.leftMargin = (realmObj.xPosition)
                params.topMargin = (realmObj.yPosition)
            }
            params.width = buttonWidth
            params.height = buttonHeight
            button.layoutParams = params
            val tolerance = 5

            // define a local function to handle touch events
            fun listenForTouchThings(view : View, motionEvent : MotionEvent) : Boolean {
                if (motionEvent.action == MotionEvent.ACTION_DOWN){
                    button.downX = motionEvent.rawX
                    button.downY = motionEvent.rawY
                }
                else if (motionEvent.action == MotionEvent.ACTION_MOVE) {
                    val newX = motionEvent.rawX - view.width/2
                    val newY = motionEvent.rawY - view.height
                    view.x = newX
                    view.y = newY
                    val realm: Realm = Realm.getDefaultInstance()
                    realm.beginTransaction()
                    realmObj.xPosition = newX.roundToInt()
                    realmObj.yPosition = newY.roundToInt()
                    realm.commitTransaction()
                }
                else if (motionEvent.action == MotionEvent.ACTION_UP) {
                    if ((button.downX - motionEvent.rawX).absoluteValue < tolerance &&
                        (button.downY - motionEvent.rawY).absoluteValue < tolerance){

                        val selectedHive = button.hive
                        viewModel.selectedHive = selectedHive

                        Log.d("YardView", "$selectedHive")

                        //Invalidate the existing navigation_hive_detail to force it to be redrawn when it appears.

                        activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.navigation_hive_detail)
                    }
                }
                return true
            }

            button.setOnTouchListener { view, motionEvent ->
                listenForTouchThings(view, motionEvent)
            }

            myButtons.add(button)
            hiveLayout.addView(button)
        }
        return root
    }

    /**
     * Called when a fragment is first attached to its context. `onCreate(Bundle)` will be called
     * after this.
     *
     * @param context The current app context
     */
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } //else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        //}
    }

    /**
     * Called when the fragment is no longer attached to its activity. This is called after
     * `onDestroy()`.
     */
    override fun onDetach() {
        super.onDetach()
        listener = null
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     *
     * See the Android Training lesson [Communicating with Other Fragments]
     * (http://developer.android.com/training/basics/fragments/communicating.html)
     * for more information.
     */
    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        /**
         * Use this factory method to create a new instance of this fragment using the provided
         * parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment YardView.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            YardView().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
