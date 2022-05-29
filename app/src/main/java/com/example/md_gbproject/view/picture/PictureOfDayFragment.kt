package com.example.md_gbproject.view.picture

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentPictureOfDayBinding
import com.example.md_gbproject.utils.WIKI_DOMAIN
import com.example.md_gbproject.view.MainActivity
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
        //вынести в отдельную функцию
        binding.textInputLayout.setEndIconOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse("$WIKI_DOMAIN${binding.inputEditText.text.toString()}")
            })
        }
        sheetBehaviorInit()

        (requireActivity() as MainActivity).setSupportActionBar(binding.bottomAppBar)
        setHasOptionsMenu(true)

        processFabClick()
        processChipGroupChecked()
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
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                //TODO()
            }
            group.findViewById<Chip>(checkedId)?.let {

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

            }
            is PictureOfDayState.Loading -> {

            }
            is PictureOfDayState.Success -> {

                if(pictureOfDayState.pictureOfDayResponseData.mediaType=="image"){
                    binding.image.load(pictureOfDayState.pictureOfDayResponseData.url) {
                        //TODO скрасить загрузку
                    }
                }else{
                    //отображение видео
                    // binding.image.load(pictureOfDayState.pictureOfDayResponseData.hdurl)
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