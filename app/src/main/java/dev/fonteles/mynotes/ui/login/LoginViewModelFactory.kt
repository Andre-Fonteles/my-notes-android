package dev.fonteles.mynotes.ui.login

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.fonteles.mynotes.data.repository.UserRepository
import dev.fonteles.mynotes.ui.main.MainViewModel

class LoginViewModelFactory(private val app: Application): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                userRepository = UserRepository.getInstance(app)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}