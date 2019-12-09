package com.example.thehiveapp_android


import android.content.Context
import android.util.Log
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.InspectionRealmObject
import org.junit.Test

import androidx.test.runner.AndroidJUnit4
import org.junit.After

import org.junit.Before
import org.junit.runner.RunWith
import java.lang.RuntimeException

import androidx.test.core.app.ApplicationProvider
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.*
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.Lock
import java.util.concurrent.locks.ReentrantLock

/**
 * Instrumented unit test to monitor the correctness of our Realm database.
 *
 * @author David and Zac
 */
@RunWith(AndroidJUnit4::class)
class RealmUnitTest {
    /*
    // companion object for facilitation manual signaling for database changes. Doesn't seem to work
    // due to Realm API weirdness
    companion object {
        private val mutex = ReentrantLock(true) as Lock

        // Realm is giving me a headache with this "not updating the database" crap, so we're doing
        // this the hard way
        var DBSynchronized : Boolean = true
            get() {
                // access the current value thread-safely
                var retVal : Boolean
                try {
                    mutex.lock()
                    retVal = field
                } finally {
                    mutex.unlock()
                }

                return retVal
            }
            set(value) {
                // modify the current value thread-safely
                try {
                    mutex.lock()
                    field = value
                } finally {
                    mutex.unlock()
                }
            }
    } */

    private val context = ApplicationProvider.getApplicationContext<Context>()

    lateinit var manager : DataManager //= DataManager.getInstance()
    lateinit var realm: Realm

    // create a listener which only lets us proceed when something in the database actually changes
    // UPDATE: only valid for threads with a "looper", which _apparently_ doesn't include our
    // testing thread, because of course it doesn't.
    //private val callback = RealmChangeListener<Realm> { DBSynchronized = true }

    @Before
    fun setup() {
        Log.i("test init", "running test setup")
        Realm.init(context)
        val realmConfig = RealmConfiguration.Builder().inMemory().name("test").build()
        realm = Realm.getInstance(realmConfig)
        //realm.addChangeListener(callback)

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

        //DBSynchronized = false
        manager.saveObject(newHive)

        // check for an update every half-second, so we're not wasting tons of CPU power opening and
        // closing locks
        //while(!DBSynchronized) { TimeUnit.MILLISECONDS.sleep(500) }


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



    /**
     * Test saveObject() and deletion methods
     */
    @Test
    fun deletionOperations() {
        try {
            // retrieve the current hive list an check that it's empty
            var hives = manager.getAllHiveLogs()
            if (!hives.isValid) {
                throw RuntimeException("Logs promise is invalid!.")
            }

            if (!hives.isEmpty()) {
                throw RuntimeException("Logs promise isn't empty, despite us not adding anything yet!")
            }

            // create new hive to add to test database
            val someHive = HiveRealmObject()
            someHive.name = "Galah"
            val someOtherHive = HiveRealmObject()
            someOtherHive.name = "Mystagogical"
            val deleteHive = HiveRealmObject()
            deleteHive.name = "Exenterate"

            manager.saveObject(someHive)
            manager.saveObject(someOtherHive)
            manager.saveObject(deleteHive)

            // get a promise for the hives we stored
            hives = manager.getAllHiveLogs()

            // wait for the result to become available
            if (!hives.load()) {
                throw RuntimeException("Could not load promise!")
            } else if (!hives.isValid) {
                throw RuntimeException("Results set is invalid")
            } else if (hives.size < 3) {
                throw RuntimeException(
                    "Results set does not contain all three objects! (size: ${hives.size})"
                )
            }

            // delete a single object
            manager.deleteObject(deleteHive)
            hives = manager.getAllHiveLogs()

            // wait for the result to become available
            if (!hives.load()) {
                throw RuntimeException("Could not load promise!")
            } else if (!hives.isValid) {
                throw RuntimeException("Results set is invalid")
            } else if (hives.size != 2) {
                throw RuntimeException(
                    "Results set does not contain two objects, but ${hives.size}!"
                )
            }

            // delete all remaining hives in the database to make sure multiple-deletion works
            manager.deleteObjectsInRealmResults(hives)
            hives = manager.getAllHiveLogs()

            // wait for the result to become available
            if (!hives.load()) {
                throw RuntimeException("Could not load promise!")
            } else if (!hives.isValid) {
                throw RuntimeException("Results set is invalid")
            } else if (hives.isNotEmpty()) {
                throw RuntimeException("Results set isn't empty!")
            }
        } finally {
            // delete the object directly, so that the database will be in a "clean" state for
            // future test cases
            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()
        }
    }


    @After
    fun tearDown() {
        //manager.tearDown()
    }
}
