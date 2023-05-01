package com.jozze.ila.presentation.splash

import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.jozze.ila.databinding.FragmentSplashBinding
import com.jozze.ila.presentation.base.BaseFragment
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class SplashFragment: BaseFragment<SplashViewModel, FragmentSplashBinding>() {

    override val mViewModel: SplashViewModel by activityViewModels()

    override fun getViewBinding() = FragmentSplashBinding.inflate(layoutInflater)

    override fun onViewReady() {
        setupObservers()
//        loadHomeScreen()
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            mViewModel.launchHomeScreen().collectLatest {
                loadHomeScreen()
            }
        }
    }

    private fun loadHomeScreen() {
//        CoroutineScope(Dispatchers.Main).launch {
//            delay(1.seconds)
            findNavController().navigate(SplashFragmentDirections.actionSplashToHome())
//        }
    }
}