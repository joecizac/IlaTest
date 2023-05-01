package com.jozze.ila.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.jozze.ila.R
import com.jozze.ila.data.model.ListItemData
import com.jozze.ila.databinding.ItemListBinding

class ListItemAdapter(val showPlaceholder: (Boolean) -> Unit) :
    ListAdapter<ListItemData, ListItemAdapter.ListItemViewHolder>(ItemDataCallback()),
    Filterable {

    private var mDataList = listOf<ListItemData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val binding = DataBindingUtil.inflate<ItemListBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_list,
            parent,
            false
        )

        return ListItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    fun setData(data: List<ListItemData>) {
        mDataList = data
        submitList(data)
    }

    inner class ListItemViewHolder(private val mBinding: ItemListBinding) :
        RecyclerView.ViewHolder(mBinding.root) {
        fun bind(listItemData: ListItemData) {
            mBinding.item = listItemData
            mBinding.executePendingBindings()
        }
    }

    override fun getFilter(): Filter {
        return ListItemFilter<ListItemData>(mDataList) {
            submitList(it)
            showPlaceholder(it.isEmpty())
        }
    }
}

