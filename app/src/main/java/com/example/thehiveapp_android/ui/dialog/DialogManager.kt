package com.example.thehiveapp_android.ui.dialog

import androidx.appcompat.app.AlertDialog
import android.content.Context
import android.widget.Toast
import java.lang.RuntimeException

/**
 * Basic AlertDialog manager.
 *
 * Singleton class which permits drawing a simple AlertDialog simply in any generic context.
 *
 * @author Zac
 */
object DialogManager {

    /**
     * Present a dialog box over the current fragment with only a single neutral action.
     *
     * @param context The context in which to draw the alert, likely from calling
     * .context or getContext() on the fragment calling this function.
     * @param title A string containing the title of the alert.
     * @param message A string containing the message body of the alert.
     */
    fun presentDialog(context: Context?, title: String, message: String) {
        // Create our AlertDialog Builder.
        val builder = AlertDialog.Builder(context ?:
            throw RuntimeException("Please don't break anything")
        )

        builder.setTitle(title) // Set the title and message.
        builder.setMessage(message)

        builder.setNeutralButton("OK") { _, _ ->
            Toast.makeText(context, "OK", Toast.LENGTH_SHORT).show()
        }

        builder.show()

    }

}