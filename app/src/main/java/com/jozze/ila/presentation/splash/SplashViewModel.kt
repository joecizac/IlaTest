package com.jozze.ila.presentation.splash

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlin.time.Duration.Companion.seconds

class SplashViewModel : ViewModel() {

    fun launchHomeScreen() = flow {
        delay(1.seconds)
        emit(true)
    }
}