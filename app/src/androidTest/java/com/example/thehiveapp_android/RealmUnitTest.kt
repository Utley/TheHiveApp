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

    /**
     * TODO replace this with a real test once things start compiling
     */
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    @Test
    fun dataManagerDoesntImplodeWhenIPokeIt() {
        var foo = DataManager.getInstance().getAllHives()

        if(!foo.isValid || foo.isEmpty()) {
            throw RuntimeException("baaaaaaaad")
        }
    }

    @After
    fun tearDown() {
        //manager.tearDown()
    }
}
