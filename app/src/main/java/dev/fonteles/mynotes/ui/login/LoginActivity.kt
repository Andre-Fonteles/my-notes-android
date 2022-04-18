package dev.fonteles.mynotes.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.fonteles.mynotes.data.FuncResult
import dev.fonteles.mynotes.databinding.ActivityLoginBinding
import dev.fonteles.mynotes.ui.main.MainActivity

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    private lateinit var viewBinding: ActivityLoginBinding

    private var reactToChange = true

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

        loginViewModel.loginResult.observe(this, Observer { loginResult ->
            when(loginResult) {
                is FuncResult.Success -> {
                    startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                    finish()
                }
                is FuncResult.Error -> {
                    Toast.makeText(this@LoginActivity, loginResult.exception.message, Toast.LENGTH_LONG).show()
                    reactToChange = false
                    viewBinding.username.text.clear()
                    viewBinding.password.text.clear()
                    reactToChange = true
                    viewBinding.progressBar.visibility = View.INVISIBLE
                }
            }

        })

        viewBinding.username.doAfterTextChanged {
            if(reactToChange) {
                loginViewModel.validateForm(
                    it.toString(),
                    viewBinding.password.text.toString()
                )
            }
        }

        viewBinding.password.doAfterTextChanged {
            if(reactToChange) {
                loginViewModel.validateForm(
                    viewBinding.username.text.toString(),
                    it.toString()
                )
            }
        }

        viewBinding.loginRegister.setOnClickListener {
            loginViewModel.login(
                viewBinding.username.text.toString(),
                viewBinding.password.text.toString()
            )
            viewBinding.progressBar.visibility = View.VISIBLE
        }
    }
}