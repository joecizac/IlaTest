package com.jozze.ila.presentation.home

import androidx.lifecycle.ViewModel
import com.jozze.ila.data.model.CarouselData
import com.jozze.ila.data.model.ListItemData
import com.jozze.ila.data.repository.DataRepository
import com.jozze.ila.util.AppConstants
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class HomeViewModel: ViewModel() {

    private val mRepository = DataRepository()

    private val _carouselDataList: MutableStateFlow<List<CarouselData>> =
        MutableStateFlow(emptyList())
    val carouselDataList = _carouselDataList.asStateFlow()

    private val _itemList: MutableStateFlow<List<ListItemData>> =
        MutableStateFlow(emptyList())
    val itemList = _itemList.asStateFlow()

    init {
        _carouselDataList.update {
            mRepository.getDummyData(AppConstants.CAROUSEL_SIZE, AppConstants.LIST_SIZE)
        }
    }

    fun getItemListing(itemList: List<ListItemData>) {
        _itemList.update {
            itemList
        }
    }

    /**
     * Get the item listing for the selected carousel item position
    * */
    fun getItemListingForCarouselPosition(position: Int): List<ListItemData> {
        return _carouselDataList.value[position].data
    }
}