package com.example.md_gbproject.view.recycle

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.ItemTouchHelper
import com.example.md_gbproject.data.RecycleData
import com.example.md_gbproject.databinding.FragmentRecycleBinding
import com.example.md_gbproject.repository.ItemTouchHelperCallback
import com.example.md_gbproject.repository.OnListItemClickListener
import com.example.md_gbproject.utils.TYPE_ITEM_EARTH
import com.example.md_gbproject.utils.TYPE_ITEM_HEADER
import com.example.md_gbproject.utils.TYPE_ITEM_MARS


class RecycleFragment : Fragment(),OnListItemClickListener {

    private var _binding: FragmentRecycleBinding? = null
    private val binding: FragmentRecycleBinding
        get() = _binding!!

    private val list = mutableListOf(
        RecycleData("Header","", TYPE_ITEM_HEADER),
        RecycleData("Earth","Description Earth", TYPE_ITEM_EARTH),
        RecycleData("Mars","Description Mars", TYPE_ITEM_MARS)
    )
    lateinit var adapter: RecycleFragmentAdapter

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
        adapter = RecycleFragmentAdapter(this)
        adapter.setList(list)
        binding.mainRecycleView.adapter = adapter
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.mainRecycleView)
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        @JvmStatic
        fun newInstance() = RecycleFragment()
    }

    override fun onItemClick(data: RecycleData) {
    }

    override fun onAddClick(position: Int) {
        list.add(position,list[position])
        adapter.setAddToList(list,position)
    }

    override fun onRemoveClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list,position)
    }

}