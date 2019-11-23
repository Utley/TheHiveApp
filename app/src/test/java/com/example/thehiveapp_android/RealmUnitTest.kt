package com.example.thehiveapp_android


import io.realm.RealmConfiguration
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

class RealmUnitTest() {

    // dummy Realm object used for testing
    // taken from https://medium.com/@q2ad/android-testing-realm-2dc1e1c94ee1
    private var testConfig =
        RealmConfiguration.Builder().inMemory().name("test-realm").build()


    @Before
    fun setup() {

    }

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
}
