package dev.fonteles.mynotes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.fonteles.mynotes.R
import dev.fonteles.mynotes.data.repository.IUserRepository

class LoginViewModel(private val userRepository: IUserRepository): ViewModel() {

    private val _formState = MutableLiveData<LoginFormState>(LoginFormState())
    val formState: LiveData<LoginFormState> = _formState

    private val usernameRegex = Regex("^[a-zA-Z]+[\\w-]{2,}\$")

    fun validateForm(username: String, password: String) {
        val usernameError = if(!usernameRegex.matches(username)) {
            R.string.ui_login_error_username
        } else {
            null
        }

        val passwordError = if(password.length < 6) {
            R.string.ui_login_error_password
        } else {
            null
        }

        _formState.value = LoginFormState(usernameError, passwordError)
    }
}