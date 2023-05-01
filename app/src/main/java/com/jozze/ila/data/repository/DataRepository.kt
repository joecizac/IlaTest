package com.jozze.ila.data.repository

import com.jozze.ila.R
import com.jozze.ila.data.model.CarouselData
import com.jozze.ila.data.model.ListItemData
import com.jozze.ila.util.AppConstants

class DataRepository {

    private val mData = mutableListOf<CarouselData>()

    fun getDummyData(
        carouselCount: Int = 1,
        itemCount: Int = 1
    ): List<CarouselData> {
        mData.clear()
        for (i in 1..carouselCount) {
            val drawable = getCarouselDrawable(i)
            val listItemData = mutableListOf<ListItemData>()
            for (j in 1..itemCount) {
                listItemData.add(
                    ListItemData(
                        carouselId = i,
                        id = j,
                        image = drawable,
                        text = "Item ${i.times(10).plus(j)}"
                    )
                )
            }
            mData.add(
                CarouselData(
                    id = i,
                    image = drawable,
                    data = listItemData
                )
            )
        }

        return mData
    }

    private fun getCarouselDrawable(index: Int): Int {
        return when(index % AppConstants.CAROUSEL_SIZE) {
            0, 1 -> R.drawable.ic_rupee
            2, 3 -> R.drawable.ic_euro
            4, 5 -> R.drawable.ic_lira
            6, 7 -> R.drawable.ic_pound
            8, 9 -> R.drawable.ic_yuan
            else -> {
                0
            }
        }
    }
}