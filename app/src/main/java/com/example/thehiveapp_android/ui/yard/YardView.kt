package com.example.thehiveapp_android.ui.yard

import android.content.ClipData
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Half.toFloat
import android.util.Log
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.marginLeft
import androidx.core.view.marginTop
import androidx.navigation.findNavController

import com.example.thehiveapp_android.R
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.RealmResults
import java.lang.Math.round

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [YardView.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [YardView.newInstance] factory method to
 * create an instance of this fragment.
 */
class YardView : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val buttonWidth = 300
        val buttonHeight = 300
        val root = inflater.inflate(R.layout.fragment_yard_view, container, false)
        var allHives: RealmResults<HiveRealmObject> = DataManager.instance.getAllHives()
        val myButtons = ArrayList<Button>()
        val hiveLayout: RelativeLayout = root.findViewById(R.id.hiveContainer)
        root.setOnDragListener { it, dragEvent ->
            if(dragEvent.action == DragEvent.ACTION_DRAG_STARTED) {
//
//            if(dragEvent.action == DragEvent.ACTION_DROP) {
//                var tmpParams: FrameLayout.LayoutParams  = FrameLayout.LayoutParams(buttonWidth, buttonHeight)
//                tmpParams.width = buttonWidth
//                tmpParams.height = buttonHeight
////                    tmpParams.leftMargin = button.left + round(dragEvent.x)
////                    tmpParams.topMargin = button.top + round(dragEvent.y)
//                tmpParams.leftMargin = round(dragEvent.x) - it.width / 2
//                tmpParams.topMargin = round(dragEvent.y) - it.height / 2
//                it.layoutParams = tmpParams
////                    button.top = round(dragEvent.y)
////                    button.left = round(dragEvent.x)
////                    button.width = buttonWidth
////                    button.height = buttonHeight
//
                true
            }
            else true
        }


        for (i in 0..allHives.size-1) {
            val button = Button(this.context)
            button.setBackgroundColor(getResources().getColor(R.color.colorPrimary))
            val realmObj: HiveRealmObject? = allHives.get(i)
            button.text = realmObj?.name
            button.width = 50
            button.height = 50

            val columnCount = 3
//            var params: RelativeLayout.LayoutParams  = RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT)
            var params: RelativeLayout.LayoutParams  = RelativeLayout.LayoutParams(buttonWidth, buttonHeight)
            params.leftMargin = buttonWidth * (i % columnCount) + 10
            params.topMargin = buttonHeight * (i / columnCount) + 10
            params.width = buttonWidth
            params.height = buttonHeight
            button.layoutParams = params

            button.setOnLongClickListener {
               button.startDragAndDrop(null,View.DragShadowBuilder(it),button,0)
                true
            }
            button.setOnDragListener { it, dragEvent ->
                Log.i("hive","drag event ${dragEvent.toString()}")
                if(dragEvent.action == DragEvent.ACTION_DROP) {
                    Log.i("hive","button ${button.text} got dropped!")

                    var tmpParams: RelativeLayout.LayoutParams  = RelativeLayout.LayoutParams(buttonWidth, buttonHeight)
                    tmpParams.width = buttonWidth
                    tmpParams.height = buttonHeight
                    tmpParams.leftMargin = button.left + round(dragEvent.x)
                    tmpParams.topMargin = button.top + round(dragEvent.y)
                    tmpParams.leftMargin = round(dragEvent.x) - button.width / 2
                    tmpParams.topMargin = round(dragEvent.y) - button.height / 2
                    tmpParams.alignWithParent = true
                    Log.i("hive","left pre ${button.left}")
                    Log.i("hive","top pre ${button.top}")
                    Log.i("hive","drag x ${dragEvent.x}")
                    Log.i("hive","drag y ${dragEvent.y}")
                    button.layoutParams = tmpParams
                    Log.i("hive","left post ${button.left}")
                    Log.i("hive","top post ${button.top}")
                    true
                }
                else true
            }

            button.setOnClickListener {
                activity?.findNavController(R.id.nav_host_fragment)?.navigate(R.id.navigation_hive_form)
            }
            myButtons.add(button)
            hiveLayout.addView(button)
        }
        return root
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
//            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

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
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
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
