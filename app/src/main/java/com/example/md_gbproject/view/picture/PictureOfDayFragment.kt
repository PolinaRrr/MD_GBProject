package com.example.md_gbproject.view.picture

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bumptech.glide.Glide
import com.example.md_gbproject.databinding.FragmentPictureOfDayBinding
import com.example.md_gbproject.utils.MEDIA_TYPE_IMAGE
import com.example.md_gbproject.utils.SURPRISE_IMAGE
import com.example.md_gbproject.viewmodel.PictureOfDayState
import com.example.md_gbproject.viewmodel.PictureOfDayViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip

class PictureOfDayFragment : Fragment() {

    private var _binding: FragmentPictureOfDayBinding? = null
    private val binding: FragmentPictureOfDayBinding
        get() = _binding!!

    private val viewModel: PictureOfDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfDayViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfDayBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendRequest()
        sheetBehaviorInit()
        processChipGroupChecked()
    }

    private fun processChipGroupChecked() {
        binding.chipGroup.setOnCheckedChangeListener { group, position ->
            when (position) {
                1 -> {
                    viewModel.sendRequest()
                }
                2 -> {
                    viewModel.sendRequestForYesterday()
                }
                3 -> {
                    viewModel.sendRequestForBeforeYesterday()
                }
            }
            group.findViewById<Chip>(position)?.let {

            }
        }
    }

    private fun sheetBehaviorInit() {
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.bottomSheet.bottomSheetContainer)

        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HALF_EXPANDED
        bottomSheetBehavior.addBottomSheetCallback(object :
            BottomSheetBehavior.BottomSheetCallback() {
            override fun onStateChanged(bottomSheet: View, newState: Int) {
                when (newState) {
                    BottomSheetBehavior.STATE_HALF_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_DRAGGING -> {
                    }
                    BottomSheetBehavior.STATE_HIDDEN -> {
                    }
                    BottomSheetBehavior.STATE_SETTLING -> {
                    }
                    BottomSheetBehavior.STATE_EXPANDED -> {
                    }
                    BottomSheetBehavior.STATE_COLLAPSED -> {
                    }
                }
            }

            override fun onSlide(bottomSheet: View, slideOffset: Float) {

            }
        })
    }

    private fun renderData(pictureOfDayState: PictureOfDayState) {

        when (pictureOfDayState) {
            is PictureOfDayState.Error -> {
                Log.d("@@@","error ${pictureOfDayState.error}")
                Glide.with(this).load(SURPRISE_IMAGE).into(binding.image)
            }
            is PictureOfDayState.Loading -> {

            }
            is PictureOfDayState.Success -> {

                if (pictureOfDayState.pictureOfDayResponseData.mediaType == MEDIA_TYPE_IMAGE) {

                    binding.image.load(pictureOfDayState.pictureOfDayResponseData.url)

                } else {
                    //отображение видео
                    Glide.with(this).load(SURPRISE_IMAGE).into(binding.image)
                }

                binding.bottomSheet.textViewTitle.text =
                    pictureOfDayState.pictureOfDayResponseData.title

                binding.bottomSheet.textViewAnnotation.text =
                    pictureOfDayState.pictureOfDayResponseData.explanation
            }
        }
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfDayFragment()
    }
}