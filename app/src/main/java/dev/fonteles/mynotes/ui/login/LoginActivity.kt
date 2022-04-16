package dev.fonteles.mynotes.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dev.fonteles.mynotes.R
import dev.fonteles.mynotes.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
    }
}