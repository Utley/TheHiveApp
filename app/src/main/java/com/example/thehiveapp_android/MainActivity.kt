package com.example.thehiveapp_android

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import io.realm.Realm

//This is the main entrypoint for the app.
/**
 * App entrypoint
 *
 * This class represents the main entrypoint for the app; it's similar in practice to the `main()`
 * method of a Java program
 *
 * @author probably Zac?
 */
class MainActivity : AppCompatActivity() {

    /**
     * App entrypoint function
     *
     * This function acts like the `main()` method of a Java program
     *
     * @param savedInstanceState ???
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
                R.id.navigation_home, R.id.navigation_notifications, R.id.navigation_hive_form, R.id.navigation_hive_list
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
    }
}