package com.example.thehiveapp_android.ui.dialog

import android.app.AlertDialog
import android.content.Context
import android.widget.Toast

/**
 * Class to make building certain AlertDialog objects less tedious.
 *
 * @author Zac
 */
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

    /**
     * Present a dialog box over the current fragment with only a single neutral action.
     *
     * @param context The context in which to draw the alert, likely from calling
     * .context or getContext() on the fragment calling this function.
     * @param title A string containing the title of the alert.
     * @param message A string containing the message body of the alert.
     */
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