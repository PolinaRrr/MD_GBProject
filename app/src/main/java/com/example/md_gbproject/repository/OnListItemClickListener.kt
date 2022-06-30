package com.example.md_gbproject.repository

import com.example.md_gbproject.data.RecycleData

interface OnListItemClickListener {
    fun onItemClick(data: RecycleData)
    fun onAddClick(position: Int)
    fun onRemoveClick(position: Int)

}