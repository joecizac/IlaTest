package com.jozze.ila.data.model

import androidx.annotation.DrawableRes

data class ListItemData(
    val carouselId: Int,
    val id: Int,
    @DrawableRes val image: Int,
    val text: String
)