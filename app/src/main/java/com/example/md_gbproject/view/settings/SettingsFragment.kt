package com.example.md_gbproject.view.settings

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentSettingsBinding
import com.example.md_gbproject.utils.CHOSEN_THEME
import com.example.md_gbproject.utils.LOCAL_SP
import com.google.android.material.chip.Chip
import com.google.android.material.tabs.TabLayout
class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null
    private val binding: FragmentSettingsBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val localContext: Context = ContextThemeWrapper(activity, getIdTheme(getCurrentIdTheme()))
        val localInflater = inflater.cloneInContext(localContext)
        _binding = FragmentSettingsBinding.inflate(localInflater)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> {
                        setCurrentTheme(R.style.BlueGreyThemeProject)
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.settings_container,newInstance())
                            .commit()
                    }
                    1 -> {
                        setCurrentTheme(R.style.TealThemeProject)
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.settings_container, newInstance())
                            .commit()
                    }
                    2 -> {
                        setCurrentTheme(R.style.MagentaThemeProject)
                        requireActivity().supportFragmentManager
                            .beginTransaction()
                            .replace(R.id.settings_container,newInstance())
                            .commit()
                    }
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

                Log.d("@@@","${tab?.position} tab unselected ")
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {
                Log.d("@@@","${tab?.position} tab reelected ")

            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }


    private fun processChipGroupChecked() {
        binding.chipGroup.setOnCheckedChangeListener { group, position ->
            when (position) {
                1 -> {
                }
                2 -> {
                }
                3 -> {
                }
            }
            group.findViewById<Chip>(position)?.let {

            }
        }
    }

    private fun getIdTheme(idTheme: Int): Int {
        return idTheme
    }

    private fun getCurrentIdTheme(): Int {
        val sharedPreferences =
            requireActivity().getSharedPreferences(LOCAL_SP, AppCompatActivity.MODE_PRIVATE)
        return sharedPreferences.getInt(CHOSEN_THEME, -1)
    }

    private fun setCurrentTheme(idTheme: Int) {
        val sharedPreferences =
            requireActivity().getSharedPreferences(LOCAL_SP, AppCompatActivity.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putInt(CHOSEN_THEME,idTheme)
        editor.apply()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = SettingsFragment()
    }
}