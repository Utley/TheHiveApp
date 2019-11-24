package com.example.thehiveapp_android


import com.example.thehiveapp_android.data.DataManager
import io.realm.Realm
import io.realm.RealmConfiguration
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


/**
 * Unit test to monitor the correctness of our Realm database.
 *
 * @author David
 */
class RealmUnitTest {

    // dummy Realm object used for testing
    // taken from https://medium.com/@q2ad/android-testing-realm-2dc1e1c94ee1
    private var testConfig =
        RealmConfiguration.Builder().inMemory().name("test-realm").build()

    private val manager = DataManager.getInstance(Realm.getInstance(testConfig))

    @Before
    fun setup() {

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
