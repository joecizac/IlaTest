package com.jozze.ila.presentation.home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.PagerAdapter
import com.jozze.ila.R
import com.jozze.ila.data.model.CarouselData
import com.jozze.ila.databinding.ItemCarouselBinding

class CarouselAdapter : PagerAdapter() {

    private var mDataList: List<CarouselData> = arrayListOf()

    override fun isViewFromObject(view: View, Object: Any): Boolean {
        return view === Object as AppCompatImageView
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = DataBindingUtil.inflate<ItemCarouselBinding>(
            LayoutInflater.from(container.context),
            R.layout.item_carousel,
            container,
            false
        )

        binding.ivCarousalImage.setImageResource(mDataList[position].image)
        container.addView(binding.root)
        return binding.root
    }

    override fun getCount(): Int = mDataList.size

    override fun destroyItem(container: ViewGroup, position: Int, Object: Any) {
        container.removeView(Object as AppCompatImageView)
    }

    fun addItems(list: List<CarouselData>) {
        mDataList = list
        notifyDataSetChanged()
    }
}