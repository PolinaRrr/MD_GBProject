package com.example.md_gbproject.view.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.md_gbproject.data.RecycleData
import com.example.md_gbproject.databinding.FragmentEarthRecycleBinding
import com.example.md_gbproject.databinding.FragmentMarsRecycleBinding
import com.example.md_gbproject.utils.TYPE_ITEM_EARTH
import com.example.md_gbproject.utils.TYPE_ITEM_MARS

class RecycleFragmentAdapter(private var list: List<RecycleData>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_ITEM_EARTH -> {
                val view = FragmentEarthRecycleBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(view.root)
            }
            TYPE_ITEM_MARS -> {
                val view = FragmentMarsRecycleBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(view.root)
            }
            else -> {
                val view = FragmentEarthRecycleBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(view.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when(getItemViewType(position)){
            TYPE_ITEM_EARTH -> {
                (holder as EarthViewHolder).bindText(list[position])
            }
            TYPE_ITEM_MARS -> {
                (holder as MarsViewHolder).bindText(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].type
    }

    class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bindText(data: RecycleData){
            FragmentEarthRecycleBinding.bind(itemView).apply {
                titleEarthRecycle.text = data.title
                descriptionEarth.text = data.description
            }
        }
    }

    class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindText(data: RecycleData){
            FragmentMarsRecycleBinding.bind(itemView).apply {
                titleMarsRecycle.text = data.title
                descriptionMars.text = data.description
                supportingTextMars.text = data.description
            }
        }
    }
}