package dev.fonteles.mynotes.ui.main

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import dev.fonteles.mynotes.data.repository.UserRepository

class MainViewModelFactory(private val app: Application): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(
                userRepository = UserRepository.getInstance(app)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }

}