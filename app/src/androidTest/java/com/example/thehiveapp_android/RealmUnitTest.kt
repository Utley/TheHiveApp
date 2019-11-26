package com.example.thehiveapp_android


import android.content.Context
import android.util.Log
import com.example.thehiveapp_android.data.DataManager
import io.realm.Realm
import io.realm.RealmConfiguration
import org.junit.Test

import androidx.test.runner.AndroidJUnit4
import androidx.test.runner.AndroidJUnitRunner
import org.junit.After

import org.junit.Assert.*
import org.junit.Before
import org.junit.runner.RunWith
import java.lang.RuntimeException
import kotlin.concurrent.thread

import androidx.test.core.app.ApplicationProvider
import com.example.thehiveapp_android.data.HiveRealmObject
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.BeforeClass

/**
 * Instrumented unit test to monitor the correctness of our Realm database.
 *
 * @author David
 */
@RunWith(AndroidJUnit4::class)
class RealmUnitTest {

    val context = ApplicationProvider.getApplicationContext<Context>()

    lateinit var manager : DataManager //= DataManager.getInstance()
    lateinit var realm: Realm

    @Before
    fun setup() {
        Log.i("test init", "running test setup")
        // it doesn't feel like this should be necessary, but it keeps crashing when I try to run it
        // without init'ing Realm first
        // might just be my emulator, Idk
        Realm.init(context)
        val realmConfig = RealmConfiguration.Builder().inMemory().name("test").build()
        realm = Realm.getInstance(realmConfig)

        manager = DataManager.getInstance(realm)
    }

    @Test
    fun dataManagerCanGetAllHives() {
        var hives = manager.getAllHives()
        if(!hives.isValid) {
            throw RuntimeException("dataManagerCanGetAllHivesEmpty failed.")
        }
        Log.d("??", "${hives.count()}")
    }


    @Test
    fun canGetHivesNotEmpty() {
        var newHive = HiveRealmObject()
        newHive.name = "Bodacious"

        realm.executeTransaction { realm ->
            realm.copyToRealm(newHive) //Add this hive synchronously.
            // async adding, as done in manager.saveObject(), likely will not work with unit testing
        }

        val hives = manager.getAllHives()
        val firstHive = hives.first()!!

        if (hives.first()!!.name != "Bodacious"){
            throw RuntimeException("Not Particularly Bodacious: ${firstHive.name}")
        }
    }



    @After
    fun tearDown() {
        //manager.tearDown()
    }
}
