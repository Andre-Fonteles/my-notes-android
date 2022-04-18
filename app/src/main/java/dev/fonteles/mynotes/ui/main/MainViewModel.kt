package dev.fonteles.mynotes.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.repository.IUserRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    val userRepository: IUserRepository
    ): ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun checkUserLoggedIn() {
        viewModelScope.launch {
            val tokenResponse = userRepository.getLocalToken()
            _isLoggedIn.value = tokenResponse is FuncResult.Success
        }
    }
}