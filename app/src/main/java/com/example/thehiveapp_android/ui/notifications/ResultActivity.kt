package com.example.thehiveapp_android.ui.notifications

//import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import androidx.appcompat.app.AppCompatActivity

import com.example.thehiveapp_android.R

/**
 * Basic activity describing the app's behavior when displaying the results of some user action.
 *
 * @author I forgot how to check
 */
class ResultActivity : AppCompatActivity() {

    /**
     * Called during object initialization to perform specific behavior.
     *
     * @param savedInstanceState If this activity is being recovered from a saved state, it will be
     *  passed by this argument, allowing the activity to define custom behavior
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.hive_form_fragment)
/*
        if (intent.getBooleanExtra("notification", false)) { //Just for confirmation
            txtTitleView.text = intent.getStringExtra("title")
            txtMsgView.text = intent.getStringExtra("message")

        }

 */
    }
}