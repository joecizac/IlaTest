package com.jozze.ila.data.model

import androidx.annotation.DrawableRes

data class CarouselData(
    val id: Int,
    @DrawableRes val image: Int,
    val data: List<ListItemData>
)