package com.example.thehiveapp_android.ui.hive

import androidx.lifecycle.ViewModel
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveLogRealmObject
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.RealmResults

class HiveListViewModel : ViewModel() {

    var hives: RealmResults<HiveRealmObject> = DataManager.instance.getAllHives()

    var selectedHive: HiveRealmObject = HiveRealmObject()

    var selectedInspection: HiveLogRealmObject = HiveLogRealmObject()

}
