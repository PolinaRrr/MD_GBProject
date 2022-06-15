package com.example.md_gbproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.ActivityApiBinding
import com.example.md_gbproject.view.navigation.viewpager.ViewPagerAdapter

class ApiActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.viewPager.adapter = ViewPagerAdapter(supportFragmentManager)
        binding.viewPagerTabLayout.setupWithViewPager(binding.viewPager)

        binding.viewPagerTabLayout.getTabAt(0)?.setIcon(R.drawable.ic_planet_earth_outline)
        binding.viewPagerTabLayout.getTabAt(1)?.setIcon(R.drawable.ic_solar_system_outline)
        binding.viewPagerTabLayout.getTabAt(2)?.setIcon(R.drawable.ic_planet_mars_outline)



    }
}