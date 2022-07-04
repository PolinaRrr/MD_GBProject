package com.example.md_gbproject.view.recycle

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.md_gbproject.R
import com.example.md_gbproject.data.Changes
import com.example.md_gbproject.data.RecycleData
import com.example.md_gbproject.data.createCombinedPayloads
import com.example.md_gbproject.databinding.FragmentEarthRecycleBinding
import com.example.md_gbproject.databinding.FragmentMarsRecycleBinding
import com.example.md_gbproject.databinding.FragmentRecycleHeaderBinding
import com.example.md_gbproject.repository.DiffUtilCallback
import com.example.md_gbproject.repository.ItemTouchHelperAdapter
import com.example.md_gbproject.repository.OnListItemClickListener
import com.example.md_gbproject.utils.DESCRIPTION_MARS
import com.example.md_gbproject.utils.TYPE_ITEM_EARTH
import com.example.md_gbproject.utils.TYPE_ITEM_HEADER
import com.example.md_gbproject.utils.TYPE_ITEM_MARS
import kotlin.math.max
import kotlin.math.min

class RecycleFragmentAdapter(
    private var onListItemClickListener: OnListItemClickListener,
    private var list: MutableList<RecycleData>
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {



    private var isOpenDescription = false

    fun setList(newList: List<RecycleData>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(list, newList))
        result.dispatchUpdatesTo(this)
        this.list = newList.toMutableList()
    }

    fun setAddToList(newList: List<RecycleData>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemChanged(position)
    }

    fun setRemoveToList(newList: List<RecycleData>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemRemoved(position)
    }

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
            TYPE_ITEM_HEADER -> {
                val view = FragmentRecycleHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(view.root)
            }
            else -> {
                val view = FragmentEarthRecycleBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(view.root)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        when (getItemViewType(position)) {
            TYPE_ITEM_EARTH -> {
                (holder as EarthViewHolder).bindText(list[position])
            }
            TYPE_ITEM_MARS -> {
                (holder as MarsViewHolder).bindText(list[position])
            }
            TYPE_ITEM_HEADER -> {
                (holder as HeaderViewHolder).bindText(list[position])
            }
        }
    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when (getItemViewType(position)) {
                TYPE_ITEM_EARTH -> {
                    (holder as EarthViewHolder).bindText(list[position])
                }
                TYPE_ITEM_MARS -> {
                    val result = createCombinedPayloads(payloads as List<Changes<RecycleData>>)
                    if(result.oldData.title!=result.newData.title)
                    (holder as MarsViewHolder).itemView.
                    findViewById<TextView>(R.id.title_mars_recycle).text = result.newData.title
                }
                TYPE_ITEM_HEADER -> {
                    (holder as HeaderViewHolder).bindText(list[position])
                }
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

        fun bindText(data: RecycleData) {
            FragmentEarthRecycleBinding.bind(itemView).apply {
                titleEarthRecycle.text = data.title
                descriptionEarth.text = data.description
            }
        }
    }

    inner class MarsViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindText(data: RecycleData) {
            FragmentMarsRecycleBinding.bind(itemView).apply {
                titleMarsRecycle.text = data.title
                descriptionMars.text = data.description
                supportingTextMars.text = DESCRIPTION_MARS
                recycleMarsButtonAdd.setOnClickListener {
                    onListItemClickListener.onAddClick(layoutPosition)
                }
                recycleMarsButtonDelete.setOnClickListener {
                    onListItemClickListener.onRemoveClick(layoutPosition)
                }
                recycleMarsButtonMoveDown.setOnClickListener {
                    val newPosition = min(layoutPosition + 1, list.size - 1)
                    list.removeAt(layoutPosition).apply {
                        list.add(newPosition, this).apply {
                            notifyItemMoved(layoutPosition, newPosition)
                        }
                    }
                }
                recycleMarsButtonMoveUp.setOnClickListener {

                    val newPosition = max(layoutPosition - 1, 1)
                    list.removeAt(layoutPosition).apply {
                        list.add(newPosition, this)
                        notifyItemMoved(layoutPosition, newPosition)
                    }

                }
                recycleMarsImage.setOnClickListener {
                    if (isOpenDescription) {
                        supportingTextMars.visibility = View.GONE

                    } else {
                        supportingTextMars.visibility = View.VISIBLE
                    }
                    isOpenDescription = !isOpenDescription
                }
            }
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindText(data: RecycleData) {
            FragmentRecycleHeaderBinding.bind(itemView).apply {
                recycleHeader.text = data.title
            }
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        list.removeAt(fromPosition).apply {
            list.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }


}