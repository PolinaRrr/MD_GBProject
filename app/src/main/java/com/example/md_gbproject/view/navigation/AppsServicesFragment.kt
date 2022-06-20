package com.example.md_gbproject.view.navigation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentAppsServicesBinding

class AppsServicesFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAppsServicesBinding? = null
    private val binding: FragmentAppsServicesBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAppsServicesBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardEarth.setOnClickListener(this)
        binding.cardSolarSystem.setOnClickListener(this)
        binding.cardMars.setOnClickListener(this)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AppsServicesFragment()
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_earth -> {

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, EarthFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }

            R.id.card_solar_system -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, SolarSystemFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }

            R.id.card_mars -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, MarsFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }
        }
    }
}