package com.example.mtstetaandroid.ui.movies

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.mtstetaandroid.R


interface CanPopBackstack{
    fun canPop(): Boolean
    fun popFromBackstrack()
}

class MoviesFragment : Fragment(), CanPopBackstack {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_movies, container, false)
        return root
    }

    override fun canPop(): Boolean {

        val navHostFragment = childFragmentManager.findFragmentById(R.id.nav_host_fragment)

        if (navHostFragment != null) {
            return (navHostFragment.childFragmentManager.backStackEntryCount > 0)
        }
        return false
    }

    override fun popFromBackstrack() {
        activity?.findNavController(R.id.nav_host_fragment)?.popBackStack()
    }
}