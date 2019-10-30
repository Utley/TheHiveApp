package com.example.thehiveapp_android.ui.hive

import android.view.LayoutInflater
import android.view.View

interface HiveNoteViewInterface {
    //Returns the root view/the inflated layout file.
    fun getRootView() : View

    interface  NoteSavedListener {
        //A callback function. This will be called when we need to save a note.
        fun noteWasSaved(note: String)
    }
    fun setNoteListener(listener: NoteSavedListener)

}

// class HiveNoteView(layoutInflater: LayoutInflater) : HiveNoteViewInterface, View.OnClickListener {
    //private var mRootView = layoutInflater.inflate(thehiveapp)
// }