package com.example.thehiveapp_android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

class DialogManager {

    // Let's make this a singleton class...
    companion object {
        private var managerInstance : DialogManager? = null

        val instance: DialogManager
            get() {
                if (managerInstance == null){
                    managerInstance = DialogManager()
                }
                return managerInstance!!
            }
    }

    fun presentDialog(context: Context?, title: String, message: String){
        val builder = AlertDialog.Builder(context) //Create our AlertDialog Builder.

        builder.setTitle(title) //Set the title and message.
        builder.setMessage(message)

        builder.setNeutralButton("OK") { dialog, which ->
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
        }

        builder.show()

    }

}