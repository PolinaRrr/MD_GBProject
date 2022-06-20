package com.example.md_gbproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.ActivityMainBinding
import com.example.md_gbproject.utils.CHOSEN_THEME
import com.example.md_gbproject.utils.LOCAL_SP
import com.example.md_gbproject.view.navigation.AppsServicesFragment
import com.example.md_gbproject.view.picture.PictureOfDayFragment
import com.example.md_gbproject.view.settings.SettingsFragment


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(getIdTheme(getCurrentIdTheme()))
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.item_home -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        PictureOfDayFragment.newInstance()
                    ).addToBackStack("").commit()
                }
                R.id.item_services -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        AppsServicesFragment.newInstance()
                    ).addToBackStack("").commit()
                }
                R.id.item_settings -> {
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        SettingsFragment.newInstance()
                    ).addToBackStack("").commit()
                }
            }
            true
        }
        if (savedInstanceState == null) {
            binding.bottomNavigationView.selectedItemId = R.id.item_home
        }
        val earthBadge = binding.bottomNavigationView.getOrCreateBadge(
            R.id.item_home
        )
        earthBadge.number = 13

    }

    private fun getIdTheme(idTheme: Int): Int {
        return idTheme
    }

    private fun getCurrentIdTheme(): Int {
        val sharedPreferences = getSharedPreferences(LOCAL_SP, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(CHOSEN_THEME, -1)
    }

}