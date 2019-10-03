package com.example.thehiveapp_android.ui.hive

import android.os.Bundle
import android.os.PersistableBundle
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import com.example.thehiveapp_android.R.id.*
import com.example.thehiveapp_android.data.DataManager

class HiveListActivity : AppCompatActivity() {
    private lateinit var hiveListView : ListView

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        hiveListView = findViewById<ListView>(hive_selection_list_view)

        val hiveList = DataManager.instance.getAllHives() //RealmResults<HiveRealmObject>
        //We do get to use this like an array, but we also get free updates to objects we change here.
        //i.e if we change an object in this array, those changes should be reflected in the database.

    }


}