package com.example.md_gbproject.view.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.transition.*
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentAnimationBinding

class AnimationFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentAnimationBinding? = null
    private val binding: FragmentAnimationBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAnimationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardTransition.setOnClickListener(this)

    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = AnimationFragment()
    }

    private fun changeTransition(){
        val transitionBounds = ChangeBounds()
        transitionBounds.duration = 5000
        val transitionFade = Fade()
        transitionFade.duration = 5000
        val transitionSlide = Slide()
        transitionSlide.duration = 5000
        val transitionSet = TransitionSet()
        transitionSet.addTransition(transitionFade)
        transitionSet.addTransition(transitionBounds)
        transitionSet.addTransition(transitionSlide)
        TransitionManager.beginDelayedTransition(binding.root,transitionSet)
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_transition -> {

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, TransitionFragment.newInstance())
                    .addToBackStack("")
                    .commit()

            }

//            R.id.card_coordinator -> {
//                requireActivity().supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment_container_view, CoordinatorFragment.newInstance())
//                    .addToBackStack("")
//                    .commit()
//            }
//
//            R.id.card_motion -> {
//                requireActivity().supportFragmentManager
//                    .beginTransaction()
//                    .replace(R.id.fragment_container_view, MotionFragment.newInstance())
//                    .addToBackStack("")
//                    .commit()
           // }
        }
    }
}