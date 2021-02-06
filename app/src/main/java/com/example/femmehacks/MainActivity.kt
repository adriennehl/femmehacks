package com.example.femmehacks

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.femmehacks.match.MatchFragment
import com.example.femmehacks.profile.ProfileFragment

class MainActivity : AppCompatActivity() {
    private lateinit var profileFrag: ProfileFragment
    private lateinit var matchFrag: MatchFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        profileFrag = ProfileFragment()
        matchFrag = MatchFragment()

        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        val appBarConfiguration = AppBarConfiguration(setOf(
                R.id.matchTab, R.id.profileTab))
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        navView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    setCurrentFragment(matchFrag, "Match")
                }
                R.id.navigation_profile -> {
                    setCurrentFragment(profileFrag, "Profile")
                }
            }
            true
        }
    }

    /**
     * Updates the current fragment
     */
    private fun setCurrentFragment(fragment: Fragment, tabTitle: String) {
        /* Replace fragment */
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.nav_host_fragment, fragment)
            commit()
        }
        Log.d("ME", "inside set current fragment")
    }

}