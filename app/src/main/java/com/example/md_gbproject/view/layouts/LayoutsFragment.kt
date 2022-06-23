package com.example.md_gbproject.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.md_gbproject.R
import com.example.md_gbproject.databinding.FragmentLayoutsBinding

class LayoutsFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLayoutsBinding? = null
    private val binding: FragmentLayoutsBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLayoutsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardConstraint.setOnClickListener(this)
        binding.cardCoordinator.setOnClickListener(this)
        binding.cardMotion.setOnClickListener(this)
    }


    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = LayoutsFragment()
    }


    override fun onClick(v: View) {
        when (v.id) {
            R.id.card_constraint -> {

                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, ConstraintFragment.newInstance())
                    .addToBackStack("")
                    .commit()

            }

            R.id.card_coordinator -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, CoordinatorFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }

            R.id.card_motion -> {
                requireActivity().supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_container_view, MotionFragment.newInstance())
                    .addToBackStack("")
                    .commit()
            }
        }
    }
}