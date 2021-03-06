package com.example.md_gbproject.repository

import androidx.recyclerview.widget.DiffUtil
import com.example.md_gbproject.data.Changes
import com.example.md_gbproject.data.RecycleData

class DiffUtilCallback(
    private val oldList: List<RecycleData>,
    private val newList: List<RecycleData>
) : DiffUtil.Callback() {
    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return (oldList[oldItemPosition].title == newList[newItemPosition].title
                && ((oldList[oldItemPosition].description == newList[newItemPosition].description)))
    }

    override fun getChangePayload(oldItemPosition: Int, newItemPosition: Int): Any? {
        val oldItem = oldList[oldItemPosition]
        val newItem = newList[newItemPosition]
        return Changes(oldItem, newItem)
    }
}