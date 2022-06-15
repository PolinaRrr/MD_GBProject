package com.example.md_gbproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
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
    }
}