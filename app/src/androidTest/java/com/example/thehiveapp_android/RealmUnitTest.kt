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
import io.realm.RealmResults
import java.util.concurrent.TimeUnit

/**
 * Instrumented unit test to monitor the correctness of our Realm database.
 *
 * @author David and Zac
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
     * Tests an asynchronous database operation.
     *
     * Because asynchronous database operations are, well, asynchronous, we can't just check them
     * instantaneously to see if our changes worked. This function helps us get around this by
     * providing a framework to monitor an asynchronous Realm operation using a RealmResults promise
     * object. When the promise is completed, it can then be tested to see if the results are valid.
     *
     * Regardless of success or failure, this method will automatically clear the contents of our
     * in-memory testing database when it's finished, preventing our working values from
     * contaminating later tests.
     *
     * @param T Type of RealmObject to return
     * @param results The RealmResults promise to analyze; must not be empty
     * @param verification A function that can be used to verify the results of said promise
     * @throws RuntimeException if an error occurs with loading the data, or if any part of the data
     *  analysis fails
     */
    private fun <T> verifyAsyncResult(
        results: RealmResults<T>,
        verification: (query: RealmResults<T>) -> Boolean
    ) {
        // wait for the result to become available
        if(!results.load()) {
            throw RuntimeException("Could not load promise!")
        }

        try {
            if(!results.isValid) {
                throw RuntimeException("Results set is invalid")
            }

            if(results.isEmpty()) {
                throw RuntimeException("Results set is empty!")
            }

            if(results.first() == null) {
                throw RuntimeException("Could not retrieve hive")
            }

            if(!verification(results)) {
                throw RuntimeException("Could not verify data")
            }
        } finally {
            // delete the object directly, so that the database will be in a "clean" state for
            // future test cases
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }
    }

    /**
     * Test saveObject() for hives and getAllHives()
     */
    @Test
    fun hiveOperations() {
        // retrieve the current hive list an check that it's empty
        var hives = manager.getAllHives()
        if(!hives.isValid) {
            throw RuntimeException("Hives promise is invalid!.")
        }

        if(!hives.isEmpty()) {
            throw RuntimeException("Hives promise isn't empty, despite us not adding anything yet!")
        }

        // create new hive to add to test database
        val newHive = HiveRealmObject()
        newHive.name = "Bodacious"

        manager.saveObject(newHive)

        // get a promise for the hives we stored
        hives = manager.getAllHives()

        // define a lambda to check the results of that promise
        val checkHives : (RealmResults<HiveRealmObject>) -> Boolean = {
            hives: RealmResults<HiveRealmObject> -> hives.first()!!.name != "Bodacious"
        }

        // use the above with verifyAsyncResult() to make sure the test hive took
        verifyAsyncResult(hives, checkHives)
    }



    /**
     * Test saveObject() for inspection logs and getAllHiveLogs()
     */
    @Test
    fun inspectionOperations() {
        // retrieve the current hive list an check that it's empty
        var logs = manager.getAllHiveLogs()
        if(!logs.isValid) {
            throw RuntimeException("Logs promise is invalid!.")
        }

        if(!logs.isEmpty()) {
            throw RuntimeException("Logs promise isn't empty, despite us not adding anything yet!")
        }

        // create new hive to add to test database
        val newHive = HiveRealmObject()
        newHive.name = "Autotelic"
        val insp1 = InspectionRealmObject()
        insp1.noteString = "Cromnyomancy"
        insp1.sawQueen = true
        newHive.addLog(insp1)

        manager.saveObject(newHive)

        // get a promise for the hives we stored
        logs = manager.getAllHiveLogs()

        // define a lambda to check the results of that promise
        val checkHives : (RealmResults<InspectionRealmObject>) -> Boolean = {
                logs: RealmResults<InspectionRealmObject> ->
                    logs.first()!!.noteString != "Cromnyomancy" || !logs.first()!!.sawQueen
        }

        // use the above with verifyAsyncResult() to make sure the test hive took
        verifyAsyncResult(logs, checkHives)
    }


    @After
    fun tearDown() {
        manager.tearDown()
    }
}
