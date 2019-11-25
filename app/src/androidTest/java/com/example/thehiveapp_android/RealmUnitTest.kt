package com.example.thehiveapp_android


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


/**
 * Instrumented unit test to monitor the correctness of our Realm database.
 *
 * @author David
 */
@RunWith(AndroidJUnit4::class)
class RealmUnitTest {

    // dummy Realm object used for testing
    // taken from https://medium.com/@q2ad/android-testing-realm-2dc1e1c94ee1
    private var testConfig =
        RealmConfiguration.Builder().inMemory().name("test-realm").build()

    private lateinit var manager : DataManager //= DataManager.getInstance(Realm.getInstance(testConfig))

    private val main = MainActivity()

    @Before
    fun setup() {
        // it doesn't feel like this should be necessary, but it keeps crashing when I try to run it
        // without init'ing Realm first
        // might just be my emulator, Idk

        var rrrr : Realm

        try {
            Realm.setDefaultConfiguration(testConfig)
            Realm.init(main)
            rrrr = Realm.getInstance(testConfig)
        } catch (fuckYouTooAndroid: IllegalStateException) {
            rrrr = Realm.getDefaultInstance()
        }

        manager = DataManager.getInstance(rrrr)
    }

    /**
     * TODO replace this with a real test once things start compiling
     */
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @After
    fun tearDown() {
        manager.tearDown()
    }
}
