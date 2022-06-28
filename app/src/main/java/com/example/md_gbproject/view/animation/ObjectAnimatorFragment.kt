package com.example.md_gbproject.view.animation


import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.md_gbproject.databinding.FragmentObjectAnimatorBinding
import com.example.md_gbproject.viewmodel.AppViewModel

class ObjectAnimatorFragment : Fragment() {

    private var _binding: FragmentObjectAnimatorBinding? = null
    private val binding: FragmentObjectAnimatorBinding
        get() = _binding!!

    private var isChecked = false
    private val duration = 3000L

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentObjectAnimatorBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.fab.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                ObjectAnimator.ofFloat(binding.iconAdd, View.ROTATION, 0f, 405f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.menuItem01, View.TRANSLATION_Y, -50f, -260f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.menuItem02, View.TRANSLATION_Y, -20f, -130f)
                    .setDuration(duration).start()

                binding.menuItem01.animate()
                    .alpha(1f)
                    .setDuration(duration / 2)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.menuItem01.isClickable = true
                        }
                    })
                binding.menuItem02.animate()
                    .alpha(1f)
                    .setDuration(duration / 2)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.menuItem02.isClickable = true
                        }
                    })

                binding.transparentBackground.animate()
                    .alpha(0.5f).duration = duration
            } else {
                ObjectAnimator.ofFloat(binding.iconAdd, View.ROTATION, 405f, 0f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.menuItem01, View.TRANSLATION_Y, -260f, -50f)
                    .setDuration(duration).start()
                ObjectAnimator.ofFloat(binding.menuItem02, View.TRANSLATION_Y, -130f, -20f)
                    .setDuration(duration).start()

                binding.menuItem01.animate()
                    .alpha(0f)
                    .setDuration(duration / 2)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.menuItem01.isClickable = false
                        }
                    })
                binding.menuItem02.animate()
                    .alpha(0f)
                    .setDuration(duration / 2)
                    .setListener(object : AnimatorListenerAdapter() {
                        override fun onAnimationEnd(animation: Animator?) {
                            super.onAnimationEnd(animation)
                            binding.menuItem02.isClickable = false
                        }
                    })
                binding.transparentBackground.animate()
                    .alpha(0f).duration = duration

            }
        }
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ObjectAnimatorFragment()
    }
}