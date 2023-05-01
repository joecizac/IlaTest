package com.jozze.ila.presentation.home.adapter

import androidx.recyclerview.widget.DiffUtil
import com.jozze.ila.data.model.ListItemData

class ItemDataCallback : DiffUtil.ItemCallback<ListItemData>() {
    override fun areItemsTheSame(
        oldItem: ListItemData,
        newItem: ListItemData
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: ListItemData,
        newItem: ListItemData
    ): Boolean {
        return oldItem == newItem
    }
}