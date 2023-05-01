package com.jozze.ila.presentation.home.adapter

import android.widget.Filter
import com.jozze.ila.data.model.ListItemData

class ListItemFilter<T>(
    private val dataList: List<ListItemData>,
    val onFilter: (List<T>) -> Unit
) : Filter() {
    override fun performFiltering(ch: CharSequence?): FilterResults {
        val filteredList: List<ListItemData> = if (ch.isNullOrEmpty()) {
            dataList.toMutableList()
        } else {
            dataList.filter {
                it.text.contains(ch, true)
            }
        }
        return FilterResults().apply { values = filteredList }
    }

    override fun publishResults(ch: CharSequence?, p1: FilterResults?) {
        try {
            onFilter(p1?.values as List<T>)
        } catch (e: Exception) {
            onFilter(listOf())
        }
    }
}