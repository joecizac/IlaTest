package com.jozze.ila.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding

/**
 * Base implementation of a [Fragment] that takes the associated
 * [ViewModel] [VM] and [ViewBinding] [VB]
 */
abstract class BaseFragment<VM : ViewModel, VB : ViewBinding>: Fragment() {

    protected abstract val mViewModel: VM

    protected lateinit var mViewBinding: VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewBinding = getViewBinding()
        return mViewBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onViewReady()
    }

    abstract fun getViewBinding(): VB

    abstract fun onViewReady()
}