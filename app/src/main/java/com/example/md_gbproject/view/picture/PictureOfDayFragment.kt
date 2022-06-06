package com.example.md_gbproject.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.bumptech.glide.Glide
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentPictureOfDayBinding
import com.example.md_gbproject.utils.MEDIA_TYPE_IMAGE
import com.example.md_gbproject.utils.SURPRISE_IMAGE
import com.example.md_gbproject.utils.WIKI_DOMAIN
import com.example.md_gbproject.view.MainActivity
import com.example.md_gbproject.view.settings.SettingsFragment
import com.example.md_gbproject.viewmodel.PictureOfDayState
import com.example.md_gbproject.viewmodel.PictureOfDayViewModel
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.chip.Chip

class PictureOfDayFragment : Fragment() {

    private var _binding: FragmentPictureOfDayBinding? = null
    private val binding: FragmentPictureOfDayBinding
        get() = _binding!!

    private val viewModel: PictureOfDayViewModel by lazy {
        ViewModelProvider(this)[PictureOfDayViewModel::class.java]
    }
    private var isMain = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPictureOfDayBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_bottom_bar, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.bottom_bar_favourites -> {

            }
            R.id.bottom_bar_settings -> {
                requireActivity().supportFragmentManager.beginTransaction().
                        replace(R.id.container,SettingsFragment.newInstance()).addToBackStack("").commit()
            }
            android.R.id.home -> {
                BottomNavigationDrawerFragment.newInstance()
                    .show(requireActivity().supportFragmentManager, "")
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getLiveData().observe(viewLifecycleOwner) {
            renderData(it)
        }
        viewModel.sendRequest()
        requestToWiki()
        sheetBehaviorInit()
        initOptionsMenu()
        processFabClick()
        processChipGroupChecked()
    }

    private fun initOptionsMenu(){
        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)
    }

    private fun requestToWiki() {
        binding.textInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("$WIKI_DOMAIN${binding.inputEditText.text.toString()}")
            })
        }
    }

    private fun processFabClick() {
        binding.floatingActionButton.setOnClickListener {

            if (isMain) {
                binding.bottomAppBar.navigationIcon = null
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_END
                binding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_back
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_navigation_bar)

            } else {
                binding.bottomAppBar.navigationIcon =
                    (ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_menu_bottom_bar
                    )
                            )
                binding.bottomAppBar.fabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
                binding.floatingActionButton.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_add_fab
                    )
                )
                binding.bottomAppBar.replaceMenu(R.menu.menu_bottom_bar)
            }
            isMain = !isMain
        }
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