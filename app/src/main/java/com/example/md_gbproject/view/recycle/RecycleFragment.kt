package com.example.md_gbproject.view.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.md_gbproject.data.RecycleData
import com.example.md_gbproject.databinding.FragmentRecycleBinding
import com.example.md_gbproject.utils.TYPE_ITEM_EARTH
import com.example.md_gbproject.utils.TYPE_ITEM_MARS


class RecycleFragment : Fragment() {

    private var _binding: FragmentRecycleBinding? = null
    private val binding: FragmentRecycleBinding
        get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRecycleBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val list = listOf(
            RecycleData("Earth","Description Earth", TYPE_ITEM_EARTH),
            RecycleData("Mars","Description Mars", TYPE_ITEM_MARS)
        )
        binding.mainRecycleView.adapter = RecycleFragmentAdapter(list)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecycleFragment()
    }

}