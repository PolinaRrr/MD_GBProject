package com.example.md_gbproject.view.layouts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.md_gbproject.databinding.FragmentMotionBinding
import com.example.md_gbproject.viewmodel.AppViewModel

class MotionFragment : Fragment() {

    private var _binding: FragmentMotionBinding? = null
    private val binding: FragmentMotionBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMotionBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val viewModel: AppViewModel by lazy {
        ViewModelProvider(this)[AppViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = MotionFragment()
    }


}