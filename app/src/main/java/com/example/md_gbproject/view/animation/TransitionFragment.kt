package com.example.md_gbproject.view.animation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.transition.*
import com.example.md_gbproject.databinding.FragmentTransitionBinding
import com.example.md_gbproject.viewmodel.AppViewModel

class TransitionFragment : Fragment() {

    private var _binding: FragmentTransitionBinding? = null
    private val binding: FragmentTransitionBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentTransitionBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonAnimate01.setOnClickListener {
            changeTransition()
            if (binding.buttonAnimate02.visibility == View.INVISIBLE) {
                binding.buttonAnimate02.visibility = View.VISIBLE
                binding.buttonAnimate01.visibility = View.INVISIBLE
            } else {
                binding.buttonAnimate01.visibility = View.VISIBLE
            }
        }
        binding.buttonAnimate02.setOnClickListener {
            changeTransition()
            if (binding.buttonAnimate01.visibility == View.INVISIBLE) {
                binding.buttonAnimate01.visibility = View.VISIBLE
                binding.buttonAnimate02.visibility = View.INVISIBLE

            } else {
                binding.buttonAnimate02.visibility = View.VISIBLE
            }
        }
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



    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = TransitionFragment()
    }


}