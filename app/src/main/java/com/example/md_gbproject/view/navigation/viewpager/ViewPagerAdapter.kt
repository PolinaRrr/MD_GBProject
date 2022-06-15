package com.example.md_gbproject.view.navigation.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.md_gbproject.view.navigation.EarthFragment
import com.example.md_gbproject.view.navigation.MarsFragment
import com.example.md_gbproject.view.navigation.SolarSystemFragment

class ViewPagerAdapter(private val fragmentManager: FragmentManager): FragmentStatePagerAdapter(fragmentManager) {

    private val listFragments = listOf(
        EarthFragment.newInstance(),
        SolarSystemFragment.newInstance(),
        MarsFragment.newInstance()
    )

    override fun getCount(): Int {
        return listFragments.size
    }

    override fun getItem(position: Int): Fragment {
        return listFragments[position]
    }
}