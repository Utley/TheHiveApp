package com.example.thehiveapp_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.realm.Realm


/**
 * App entrypoint.
 *
 * This class represents the main entrypoint for the app; it's similar in practice to the `main()`
 * method of a Java program.
 *
 * @author probably Zac? Apparently nobody knows...
 */
class MainActivity : AppCompatActivity() {

    companion object {
        var inst : MainActivity? = null

        fun getInstance() : MainActivity {
            return inst ?: throw Exception("How did this even get called before main got inited")
        }
    }

    init {
        inst = this
    }

    /**
     * App entrypoint function.
     *
     * This function acts like the `main()` method of a Java program, performing the necessary
     * operations to start up the app. In particular, this method guarantees the database connection
     * will be initialized and the UI elements and managers (such as the NavControllers).
     *
     * @param savedInstanceState OS-provided object providing information to init the program's
     *      state
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize the Realm Database.
        // This should only happen when the app starts.
        Realm.init(this)

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_hive_list, R.id.navigation_notifications
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}