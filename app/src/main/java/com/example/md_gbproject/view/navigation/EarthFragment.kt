package com.example.md_gbproject.view.navigation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bumptech.glide.Glide
import com.example.md_gbproject.databinding.FragmentEarthBinding
import com.example.md_gbproject.utils.MEDIA_TYPE_IMAGE
import com.example.md_gbproject.utils.SURPRISE_IMAGE
import com.example.md_gbproject.viewmodel.AppState
import com.example.md_gbproject.viewmodel.AppViewModel

class EarthFragment : Fragment() {

    private var _binding: FragmentEarthBinding? = null
    private val binding: FragmentEarthBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEarthBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendRequest()
    }

    private fun renderData(state: AppState?) {

        when (state) {
            is AppState.Error -> {
                Log.d("@@@", "error ${state.error}")
                Glide.with(this).load(SURPRISE_IMAGE).into(binding.imageEarth)
            }
            is AppState.Loading -> {

            }
            is AppState.Success -> {

                if (state.pictureOfDayResponseData.mediaType == MEDIA_TYPE_IMAGE) {

                    binding.imageEarth.load(state.pictureOfDayResponseData.url)

                } else {
                    //отображение видео
                    Glide.with(this).load(SURPRISE_IMAGE).into(binding.imageEarth)
                }
            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = EarthFragment()
    }
}