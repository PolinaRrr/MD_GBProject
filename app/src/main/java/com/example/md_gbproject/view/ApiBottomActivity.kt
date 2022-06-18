package com.example.md_gbproject.view

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.ActivityApiBottomBinding
import com.example.md_gbproject.view.navigation.AppsServicesFragment
import com.example.md_gbproject.view.picture.PictureOfDayFragment
import com.example.md_gbproject.view.settings.SettingsFragment

class ApiBottomActivity : AppCompatActivity() {
    lateinit var binding: ActivityApiBottomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityApiBottomBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.bottomNavigationView.setOnItemSelectedListener {
            item->
            when(item.itemId){
                R.id.item_home->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        PictureOfDayFragment.newInstance()
                    ).commit()
                }
                //сюда фрагмент приложений
                R.id.item_services->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        AppsServicesFragment.newInstance()
                    ).commit()
                }
                //сюда настройки приложения
                R.id.item_settings->{
                    supportFragmentManager.beginTransaction().replace(
                        R.id.fragment_container_view,
                        SettingsFragment.newInstance()
                    ).commit()
                }
            }
            true
        }
        binding.bottomNavigationView.selectedItemId = R.id.item_home

        val earthBadge = binding.bottomNavigationView.getOrCreateBadge(
            R.id.item_home
        )
        earthBadge.number = 13
        earthBadge.backgroundColor = resources.getColor(R.color.blue_gray_100)
    }
}