package dev.fonteles.mynotes.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import dev.fonteles.mynotes.R
import dev.fonteles.mynotes.ui.login.LoginActivity

class MainActivity : AppCompatActivity() {

    lateinit var mainViewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProvider(this, MainViewModelFactory(application))
            .get(MainViewModel::class.java)

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