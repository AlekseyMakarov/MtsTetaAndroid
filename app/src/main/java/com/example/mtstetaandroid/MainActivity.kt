package com.example.mtstetaandroid

import android.os.Bundle
import android.util.Log
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.room.Room
import com.example.mtstetaandroid.data.AppDatabase
import com.example.mtstetaandroid.ui.home.HomeFragment
import com.example.mtstetaandroid.ui.movies.CanPopBackstack
import com.example.mtstetaandroid.ui.movies.MoviesFragment
import com.example.mtstetaandroid.ui.user.UserFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val db = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java, "database"
        ).build()
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.setOnItemSelectedListener {
//            navView.selectedItemId = it.itemId;
            when(it.itemId){
                R.id.navigation_home -> switchNavigationFragment("home" , false, false, ::MoviesFragment)
                R.id.navigation_user -> switchNavigationFragment("user" , false, false, ::UserFragment)
            }
            true;
        }

//            it.setSelectedItemId(bottomNav.getMenu().getItem(ITEM_INDEX).getItemId()); }

//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        navView.setupWithNavController(navController)
    }

    fun onTabMainClicked(){
        val navView: BottomNavigationView = findViewById(R.id.nav_view)
        navView.selectedItemId = R.id.navigation_home
    }


    private fun switchNavigationFragment(tag: String, forceReplace: Boolean, switchNow: Boolean, createFragment: () -> Fragment){
        val currentFragment = getCurrentVisisbleFragment()

        if(currentFragment?.tag == tag && !forceReplace) return

        val savedFragment = supportFragmentManager.findFragmentByTag(tag)

        val transaction = supportFragmentManager.beginTransaction()
        if(currentFragment != null){
            transaction.hide(currentFragment)
        }

        if(savedFragment != null && !forceReplace){
            transaction.show(savedFragment)
        }else{
            if(savedFragment != null){
                transaction.remove(savedFragment)
            }

            transaction.add(R.id.container_for_fragments, createFragment(), tag)
        }

        if(switchNow){
            transaction.commitNow()
        } else{
            transaction.commit()
        }
    }

    override fun onBackPressed() {
        val fragment = getCurrentVisibleTabFragment()
        when {
            /* if current fragment is webview fragment, go back by page history */
            (fragment as? CanPopBackstack)?.canPop()?:false -> (fragment as? CanPopBackstack)?.popFromBackstrack()
            /* if current tab fragment is not main fragment, switch to it first */
            fragment?.tag != "home" -> onTabMainClicked()
            else -> super.onBackPressed()
        }
    }

    private fun getCurrentVisibleTabFragment(): Fragment? {
        return supportFragmentManager.fragments.firstOrNull { it.isVisible }
    }
    private fun getCurrentVisisbleFragment(): Fragment? {
        return supportFragmentManager.fragments.firstOrNull{it.isVisible}
    }
}