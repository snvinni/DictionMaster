package com.example.dictionmaster.feature.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dictionmaster.core.domain.usecase.UpdateUserTimeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val updateUserTimeUseCase: UpdateUserTimeUseCase
) : ViewModel() {

    fun updateUserTime() = viewModelScope.launch {
        updateUserTimeUseCase()
    }
}