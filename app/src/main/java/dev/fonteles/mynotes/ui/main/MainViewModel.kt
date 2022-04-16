package dev.fonteles.mynotes.ui.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.repository.IUserRepository
import kotlinx.coroutines.launch

class MainViewModel(val userRepository: IUserRepository): ViewModel() {

    private val _isLoggedIn = MutableLiveData<Boolean>()
    val isLoggedIn: LiveData<Boolean> = _isLoggedIn

    fun checkUserLoggedIn() {
        viewModelScope.launch {
            val tokenResponse = userRepository.getLocalToken()
            _isLoggedIn.value = tokenResponse is FuncResult.Success

            if(tokenResponse is FuncResult.Success)
                Log.i("LoggedIn", tokenResponse.data.username + " " + tokenResponse.data.hash)
        }
    }
}