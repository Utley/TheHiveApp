package com.example.thehiveapp_android


import android.content.Context
import android.util.Log
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.InspectionRealmObject
import io.realm.Realm
import io.realm.RealmConfiguration
import org.junit.Test

import androidx.test.runner.AndroidJUnit4
import org.junit.After

import org.junit.Before
import org.junit.runner.RunWith
import java.lang.RuntimeException

import androidx.test.core.app.ApplicationProvider
import com.example.thehiveapp_android.data.HiveRealmObject
import java.util.concurrent.TimeUnit

/**
 * Instrumented unit test to monitor the correctness of our Realm database.
 *
 * @author David
 */
@RunWith(AndroidJUnit4::class)
class RealmUnitTest {
    private val context = ApplicationProvider.getApplicationContext<Context>()

    lateinit var manager : DataManager //= DataManager.getInstance()
    lateinit var realm: Realm

    @Before
    fun setup() {
        Log.i("test init", "running test setup")
        Realm.init(context)
        val realmConfig = RealmConfiguration.Builder().inMemory().name("test").build()
        realm = Realm.getInstance(realmConfig)

        manager = DataManager.getInstance(realm)
    }


    /**
     * Test saveObject() for hives and getAllHives()
     */
    @Test
    fun hiveOperations() {
        // retrieve the current hive list an check that it's empty
        var hives = manager.getAllHives()
        if(!hives.isValid || !hives.isEmpty()) {
            throw RuntimeException("dataManagerCanGetAllHivesEmpty failed.")
        }

        // create new hive to add to test database
        var newHive = HiveRealmObject()
        newHive.name = "Bodacious"

        manager.saveObject(newHive)

        // wait half a second for the thing to complete
        // maybe possibly overkill but w/e
        TimeUnit.MILLISECONDS.sleep(500)

        // make sure the test hive took
        hives = manager.getAllHives()
        if(hives.first() == null || hives.first()!!.name != "Bodacious") {
            throw RuntimeException("Could not retrieve hive")
        }

        // delete the object directly, so that the database will be in a "clean" state for future
        // test cases
        realm.deleteAll()
    }



    /**
     * Test saveObject() for inspection logs and getAllHiveLogs()
     */
    @Test
    fun inspectionOperations() {
        // retrieve the current hive list an check that it's empty
        var logs = manager.getAllHiveLogs()
        if(!logs.isValid || !logs.isEmpty()) {
            throw RuntimeException("dataManagerCanGetAllHivesEmpty failed.")
        }

        // create new hive to add to test database
        var newHive = HiveRealmObject()
        newHive.name = "Autotelic"
        val insp1 = InspectionRealmObject()
        insp1.noteString = "Cromnyomancy"
        insp1.sawQueen = true
        newHive.addLog(insp1)

        manager.saveObject(newHive)

        // wait half a second for the thing to complete
        // maybe possibly overkill but w/e
        TimeUnit.MILLISECONDS.sleep(500)

        try {
            // make sure the test hive took
            logs = manager.getAllHiveLogs()
            if(logs.first() == null) {
                throw RuntimeException("Hive not stored")
            } else  {
                val dbLog = logs.first()!!
                if(dbLog.noteString != "Cromnyomancy" || !dbLog.sawQueen) {
                    throw RuntimeException("Log did not save context")
                }
            }
        } finally {
            // delete the object directly, so that the database will be in a "clean" state for future
            // test cases
            realm.deleteAll()
        }
    }


    @After
    fun tearDown() {
        manager.tearDown()
    }
}
