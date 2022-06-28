package com.example.md_gbproject.view.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.TransitionManager
import androidx.transition.TransitionSet
import com.example.md_gbproject.databinding.FragmentPhotoTransationBinding
import com.example.md_gbproject.viewmodel.AppViewModel

class PhotoTransitionFragment : Fragment() {

    private var _binding: FragmentPhotoTransationBinding? = null
    private val binding: FragmentPhotoTransationBinding
        get() = _binding!!

    private var isOpen: Boolean = false

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoTransationBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.photoTransition.setOnClickListener {
            isOpen = !isOpen
            val transitionBounds = ChangeBounds()
            transitionBounds.duration = 3000

            val transitionImage = ChangeImageTransform()
            transitionImage.duration = 3000

            val transitionSet = TransitionSet()
            transitionSet.addTransition(transitionImage)
            transitionSet.addTransition(transitionBounds)

            TransitionManager.beginDelayedTransition(binding.root, transitionSet)

            binding.photoTransition.scaleType = if (isOpen) {
                ImageView.ScaleType.CENTER_CROP
            } else {
                ImageView.ScaleType.CENTER_INSIDE
            }
            val params = (binding.photoTransition.layoutParams as ConstraintLayout.LayoutParams)
            params.height = if (isOpen) {
                ConstraintLayout.LayoutParams.MATCH_PARENT
            } else {
                ConstraintLayout.LayoutParams.WRAP_CONTENT
            }
            binding.photoTransition.layoutParams = params
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = PhotoTransitionFragment()
    }


}