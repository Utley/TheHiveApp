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

    private val manager = DataManager.getInstance(Realm.getInstance(testConfig))

    @Before
    fun setup() {

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
