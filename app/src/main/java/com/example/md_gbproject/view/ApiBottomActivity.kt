package com.example.md_gbproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.ActivityApiBottomBinding
import com.example.md_gbproject.view.navigation.EarthFragment
import com.example.md_gbproject.view.navigation.MarsFragment
import com.example.md_gbproject.view.navigation.SolarSystemFragment

class ApiBottomActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener {
            item->
            when(item.itemId){
                R.id.item_earth->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        EarthFragment.newInstance()
                    ).commit()
                }
                R.id.item_solar_system->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        SolarSystemFragment.newInstance()
                    ).commit()
                }
                R.id.item_mars->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        MarsFragment.newInstance()
                    ).commit()
                }
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.item_earth
    }
}