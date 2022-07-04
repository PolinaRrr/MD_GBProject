package com.example.md_gbproject.view.picture

import android.os.Build
import android.os.Bundle
import android.text.SpannableString
import android.text.SpannedString
import android.text.style.BulletSpan
import android.text.style.ForegroundColorSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bumptech.glide.Glide
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentPictureOfDayBinding
import com.example.md_gbproject.utils.MEDIA_TYPE_IMAGE
import com.example.md_gbproject.utils.SURPRISE_IMAGE
import com.example.md_gbproject.viewmodel.AppState
import com.example.md_gbproject.viewmodel.AppViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior

class PictureOfDayFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentPictureOfDayBinding? = null
    private val binding: FragmentPictureOfDayBinding
        get() = _binding!!

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
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
        binding.chipToday?.setOnClickListener(this)
        binding.chipYesterday?.setOnClickListener(this)
        binding.chipTdby?.setOnClickListener(this)
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


    private fun renderData(pictureOfDayState: AppState) {

        when (pictureOfDayState) {
            is AppState.Error -> {
                Log.d("@@@", "error ${pictureOfDayState.error}")
                Glide.with(this).load(SURPRISE_IMAGE).into(binding.image)
            }
            is AppState.Loading -> {

            }
            is AppState.Success -> {

                if (pictureOfDayState.pictureOfDayResponseData.mediaType == MEDIA_TYPE_IMAGE) {

                    binding.image.load(pictureOfDayState.pictureOfDayResponseData.url)

                } else {
                    //отображение видео
                    Glide.with(this).load(SURPRISE_IMAGE).into(binding.image)
                }

//                binding.bottomSheet.textViewAnnotation.typeface =
//                    Typeface.createFromAsset(requireActivity().assets,"bogart_black.ttf")

                /*для хранения результала SpannableString SpannableStringBuilder
                val spannedString: SpannedString*/

                //для изменения атрибутов уже имеющегося текста(например, цвет)
                val spannableString: SpannableString =
                    createRainbowTitle(pictureOfDayState.pictureOfDayResponseData.title)

                /*расширенный функционал, может и добавлять символы и удалять и менять атрибуты
                val spannableStringBuilder: SpannableStringBuilder
                */
                
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    spannableString.setSpan(
                        BulletSpan(
                            5,
                            ContextCompat.getColor(
                                requireContext(),
                                R.color.mg_theme_light_onSurface
                            ),
                            10
                        ), 0, 10, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE
                    )
                }

                binding.bottomSheet.textViewTitle.text = spannableString

                binding.bottomSheet.textViewAnnotation.text =
                    pictureOfDayState.pictureOfDayResponseData.explanation
            }
        }
    }


    private fun createRainbowTitle(string: String): SpannableString {
        val arr = string.toCharArray()

        Log.d("@@@", arr.lastIndex.toString())
        Log.d("@@@", arr.size.toString())

        val spanStr = SpannableString(string)
        val colorArr = arrayOf(
            R.color.red,
            R.color.orange,
            R.color.yellow,
            R.color.green,
            R.color.cyan,
            R.color.blue,
            R.color.violet
        )
        var numSpace = 0
        for (i in arr.indices) {
            if (arr[i] == ' ') {
                numSpace++
            } else {
                spanStr.setSpan(
                    ForegroundColorSpan(
                        ContextCompat.getColor(
                            requireContext(), colorArr[(i - numSpace) % colorArr.size]
                        ),
                    ), i, i + 1, SpannedString.SPAN_EXCLUSIVE_EXCLUSIVE
                )
            }
        }
        return spanStr
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PictureOfDayFragment()
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.chip_today -> {
                viewModel.sendRequest()
            }
            R.id.chip_yesterday -> {
                viewModel.sendRequestForYesterday()
            }
            R.id.chip_tdby -> {
                viewModel.sendRequestForBeforeYesterday()
            }
        }
    }
}