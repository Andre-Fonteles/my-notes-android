package dev.fonteles.mynotes.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dagger.hilt.android.AndroidEntryPoint
import dev.fonteles.mynotes.R
import dev.fonteles.mynotes.ui.login.LoginActivity

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel.isLoggedIn.observe(this, Observer {
            val loggedIn = it ?: return@Observer

            if(!loggedIn) {
                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            } else {
                Log.i("LoggedIn", loggedIn.toString())
            }
        })

        mainViewModel.checkUserLoggedIn()
    }
}