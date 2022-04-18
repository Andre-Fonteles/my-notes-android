package dev.fonteles.mynotes.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.fonteles.mynotes.R
import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.data.model.Token
import dev.fonteles.mynotes.data.repository.IUserRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginViewModel(
    private val userRepository: IUserRepository,
): ViewModel() {

    private val _formState = MutableLiveData<LoginFormState>()
    val formState: LiveData<LoginFormState> = _formState

    private val _loginResult = MutableLiveData<FuncResult<Token>>()
    val loginResult: LiveData<FuncResult<Token>> = _loginResult

    private val usernameRegex = Regex("^[a-zA-Z]+[\\w-]{2,}\$")

    fun login(username: String, password: String) {
        viewModelScope.launch {
            _loginResult.value = userRepository.login(username, password)
        }
    }

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