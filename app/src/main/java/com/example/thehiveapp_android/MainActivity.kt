package com.example.thehiveapp_android

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.thehiveapp_android.data.DataManager
import com.example.thehiveapp_android.data.HiveRealmObject
import io.realm.Realm


/**
 * Main entrypoint for the app.
 *
 * This class acts as the main entrypoint for the app, responsible for initializing the Realm
 * database and GUI components.
 *
 * @param none None because no args
 * @property none capital-P Properties
 * @author Zac
 * @since version 0.0
 */
class MainActivity : AppCompatActivity() {

    /**
     * Specifies the app's behavior on startup.
     *
     * Acts as the app's entry point, similar to a `main()` function from Java; responsible for
     * first-time initialization of our connection to the Realm database, as well as specifying the
     * opening menu configuration.
     *
     * @param savedInstanceState ???
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Initialize the Realm Database.
        Realm.init(this) //This should only happen when the app starts.

        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_home, R.id.navigation_hive_diagram, R.id.navigation_notifications, R.id.navigation_hive_form, R.id.navigation_hive_list
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}
