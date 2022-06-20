package com.example.md_gbproject.view.navigation

import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bumptech.glide.Glide
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentSolarSystemBinding
import com.example.md_gbproject.utils.CHOSEN_THEME
import com.example.md_gbproject.utils.LOCAL_SP
import com.example.md_gbproject.utils.MEDIA_TYPE_IMAGE
import com.example.md_gbproject.utils.SURPRISE_IMAGE
import com.example.md_gbproject.viewmodel.PictureOfDayState
import com.example.md_gbproject.viewmodel.PictureOfDayViewModel

class SolarSystemFragment : Fragment() {

    private var _binding: FragmentSolarSystemBinding? = null
    private val binding: FragmentSolarSystemBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSolarSystemBinding.inflate(inflater,container, false)
        return binding.root
    }

    private val viewModel: PictureOfDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfDayViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendRequestForBeforeYesterday()
    }

    private fun renderData(pictureOfDayState: PictureOfDayState?) {

        when (pictureOfDayState) {
            is PictureOfDayState.Error -> {
                Log.d("@@@","error ${pictureOfDayState.error}")
                Glide.with(this).load(SURPRISE_IMAGE).into(binding.imageSolarSystem)
            }
            is PictureOfDayState.Loading -> {

            }
            is PictureOfDayState.Success -> {

                if (pictureOfDayState.pictureOfDayResponseData.mediaType == MEDIA_TYPE_IMAGE) {

                    binding.imageSolarSystem.load(pictureOfDayState.pictureOfDayResponseData.url)

                } else {
                    //отображение видео
                    Glide.with(this).load(SURPRISE_IMAGE).into(binding.imageSolarSystem)
                }

            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
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
        fun newInstance() = SolarSystemFragment()
    }
}