package dev.fonteles.mynotes.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.fonteles.mynotes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        loginViewModel = ViewModelProvider(this, LoginViewModelFactory(application))
            .get(LoginViewModel::class.java)

        loginViewModel.formState.observe(this, Observer { formState ->
            val usernameError = formState.usernameError
            if(usernameError != null) {
                viewBinding.username.error = getString(usernameError)
            } else {
                viewBinding.username.error = null
            }

            val passwordError = formState.passwordError
            if(passwordError != null) {
                viewBinding.password.error = getString(passwordError)
            } else {
                viewBinding.password.error = null
            }

            viewBinding.loginRegister.isEnabled = formState.isValid()
        })

        viewBinding.username.doAfterTextChanged {
            loginViewModel.validateForm(
                it.toString(),
                viewBinding.password.text.toString()
            )
        }

        viewBinding.password.doAfterTextChanged {
            loginViewModel.validateForm(
                viewBinding.username.text.toString(),
                it.toString()
            )
        }
    }
}