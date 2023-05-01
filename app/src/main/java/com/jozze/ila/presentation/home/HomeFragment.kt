package com.jozze.ila.presentation.home

import android.view.inputmethod.EditorInfo
import android.widget.TextView.OnEditorActionListener
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.viewpager.widget.ViewPager
import com.jozze.ila.databinding.FragmentHomeBinding
import com.jozze.ila.presentation.base.BaseFragment
import com.jozze.ila.presentation.home.adapter.CarouselAdapter
import com.jozze.ila.presentation.home.adapter.ListItemAdapter
import com.jozze.ila.util.TextChangeListener
import com.jozze.ila.util.gone
import com.jozze.ila.util.hideKeyboard
import com.jozze.ila.util.visible
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {

    override val mViewModel: HomeViewModel by activityViewModels()
    private lateinit var mListItemAdapter: ListItemAdapter
    private lateinit var mCarouselAdapter: CarouselAdapter

    private val mTextChangeListener by lazy {
        TextChangeListener {
            mListItemAdapter.filter.filter(it)
        }
    }

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)

    override fun onViewReady() {
        setupCarousel()
        setupObservers()
    }

    private fun setupCarousel() {
        mCarouselAdapter = CarouselAdapter()
        mViewBinding.pagerCarousel.adapter = mCarouselAdapter
        mViewBinding.tabIndicator.setupWithViewPager(mViewBinding.pagerCarousel, true)

        mViewBinding.pagerCarousel.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {}

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                mViewModel.getItemListing(
                    mViewModel.getItemListingForCarouselPosition(
                        position
                    )
                )

                val searchedTerm = mViewBinding.etSearch.text.toString()
                if (searchedTerm.isNotEmpty()) {
                    mListItemAdapter.filter.filter(searchedTerm)
                }
            }
        })

        mViewModel.getItemListing(mViewModel.getItemListingForCarouselPosition(0))

        setupItemList()
    }

    private fun setupItemList() {
        mListItemAdapter = ListItemAdapter {
            if (it) {
                mViewBinding.viewPlaceholder.visible()
            } else {
                mViewBinding.viewPlaceholder.gone()
            }
        }
        mViewBinding.rvList.run {
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = mListItemAdapter
        }

        setupSearchBar()
    }

    private fun setupSearchBar() {
        mViewBinding.etSearch.addTextChangedListener(mTextChangeListener)

        mViewBinding.etSearch.setOnEditorActionListener(OnEditorActionListener { _, actionId, _ ->
            if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                mViewBinding.etSearch.hideKeyboard()
                return@OnEditorActionListener true
            }
            false
        })

        mViewBinding.etSearch.setOnFocusChangeListener { _, hasFocus ->
            if (hasFocus) {
                mViewBinding.layoutRoot.transitionToEnd()
            }
        }

        mViewBinding.etSearch.setOnClickListener {
            mViewBinding.layoutRoot.transitionToEnd()
            mViewBinding.layoutRoot.requestFocus()
        }
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            mViewModel.carouselDataList.collectLatest {
                mCarouselAdapter.addItems(it)
            }
        }

        lifecycleScope.launch {
            mViewModel.itemList.collectLatest {
                mListItemAdapter.setData(it)
            }
        }
    }

    override fun onDestroyView() {
        mViewBinding.etSearch.removeTextChangedListener(mTextChangeListener)
        super.onDestroyView()
    }
}